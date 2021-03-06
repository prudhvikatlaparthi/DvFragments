package com.pru.navigationcomponentdemo.utils

import com.pru.navigationcomponentdemo.models.VehicleItem


object ObjectHolder {
    private val vehiclesList: ArrayList<VehicleItem> = arrayListOf()
    private var selectedVehicle: VehicleItem? = null

    fun resetVehicles() {
        vehiclesList.clear()
    }

    fun setVehicles(list: ArrayList<VehicleItem>) {
        vehiclesList.addAll(list)
    }

    fun getVehicles(): ArrayList<VehicleItem> = vehiclesList

    fun getSelectedVehicle() = selectedVehicle

    fun setSelectedVehicle(vehicleItem: VehicleItem?) {
        selectedVehicle = vehicleItem
    }

    fun resetSelectedVehicle() {
        selectedVehicle = null
    }
}