package com.pru.navigationcomponentdemo.dvir.adapters

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.LayoutInteriorItemBinding
import com.pru.navigationcomponentdemo.models.InteriorItem
import com.pru.navigationcomponentdemo.utils.Constants


class InteriorAdapter(
    private val listener: ((position: Int) -> Unit)? = null,
    private val dataList: ArrayList<InteriorItem>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return InteriorViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_interior_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InteriorViewHolder -> {
                holder.bind(dataList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class InteriorViewHolder
    constructor(
        private val binding: LayoutInteriorItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InteriorItem) = with(binding) {
            val context = tvTitle.context
            tvTitle.text = item.itemName
            val drawable = ContextCompat.getDrawable(context, item.itemIcon)
            drawable?.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(context, R.color.black),
                    PorterDuff.Mode.SRC_IN
                )
            imgIcon.setImageDrawable(drawable)
            when (item.itemCheckValidation.itemCheckStatus) {
                Constants.ItemCheckStatus.NONE -> {
                    imgIcon.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            android.R.color.darker_gray
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
                Constants.ItemCheckStatus.PASS -> {
                    imgIcon.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            android.R.color.holo_green_light
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
                else -> {
                    imgIcon.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            android.R.color.holo_red_light
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
            }

            itemRootView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    listener?.invoke(adapterPosition)
            }
        }
    }
}

