package com.pru.navigationcomponentdemo.utils

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Base64OutputStream
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil
import kotlin.math.ln
import kotlin.math.pow

const val DATE_TIME_FORMAT = "yyyyMMdd_HHmmss"

fun Context.createImageFile(): File? {
    val timeStamp = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(Date())
    val imageFileName = "IMG_" + timeStamp + "_"
    val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(imageFileName, ".png", storageDir)
}

fun getBase64String(bitmap: Bitmap?): String? {
    if (bitmap == null) return null
    var bytes: ByteArray? = null
    val output = ByteArrayOutputStream()
    try {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
        bytes = output.toByteArray()
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return if (bytes != null) Base64.encodeToString(bytes, Base64.DEFAULT) else null
}

fun decodeFile(f: File?): Bitmap? {
    var b: Bitmap? = null
    val o = BitmapFactory.Options()
    o.inJustDecodeBounds = true
    var fis: FileInputStream? = null
    try {
        fis = FileInputStream(f)
        BitmapFactory.decodeStream(fis, null, o)
        val finalPic = ExifInterface(f!!.absolutePath)
        var rotation = 0
        val orientation = finalPic.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                rotation = 90
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> {
                rotation = 270
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                rotation = 180
            }
        }
        fis.close()
        val imageMaxSize = 1024
        var scale = 1
        if (o.outHeight > imageMaxSize || o.outWidth > imageMaxSize) {
            scale = 2.0.pow(
                ceil(
                    ln(
                        imageMaxSize /
                                o.outHeight.coerceAtLeast(o.outWidth).toDouble()
                    ) / ln(0.5)
                )
            ).toInt()
        }

        val o2 = BitmapFactory.Options()
        o2.inSampleSize = scale
        fis = FileInputStream(f)
        b = BitmapFactory.decodeStream(fis, null, o2)
        val finalPicUsingMatrix = Matrix()
        finalPicUsingMatrix.postRotate(rotation.toFloat())
        val finalBitmapImage =
            Bitmap.createBitmap(b!!, 0, 0, b.width, b.height, finalPicUsingMatrix, true)
        b = finalBitmapImage
        fis.close()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fis?.close()
        return b
    }
}

fun Context.getRealPathFromUri(contentUri: Uri): String? {
    var cursor: Cursor? = null
    return try {
        val pro = arrayOf(MediaStore.MediaColumns.DATA)
        cursor = contentResolver.query(contentUri, pro, null, null, null)
        val columnIndex: Int = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        cursor.moveToFirst()
        cursor.getString(columnIndex)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    } finally {
        cursor?.close()
    }
}

fun File.convertImageFileToBase64(): String? {
    try {
        return ByteArrayOutputStream().use { outputStream ->
            Base64OutputStream(outputStream, Base64.DEFAULT).use { base64FilterStream ->
                this.inputStream().use { inputStream ->
                    inputStream.copyTo(base64FilterStream)
                }
            }
            return@use outputStream.toString()
        }
    } catch (e: OutOfMemoryError) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return null
}