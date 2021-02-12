package com.adityabugalia.playingwithlights.utils

import com.adityabugalia.playingwithlights.viewmodels.MainActivityViewModel

object DeviceCalibrations {

    fun updateDeviceList(deviceId: Int, brightnessValue: Int, viewModel: MainActivityViewModel) {

        viewModel.updateTotalBrightness(0)
        viewModel.deviceList.forEach {
            if (it.deviceId == deviceId) {
                it.deviceBrightnessLevel = brightnessValue
            }
            viewModel.increaseTotalBrightness(it.deviceBrightnessLevel)
        }
        viewModel.onDeviceSatusUpdate.postValue(true)
        viewModel.onMainBrightnessChange.postValue(viewModel.getTotalBrightness())
    }

    fun updateDeviceList(brightnessValue: Int, viewModel: MainActivityViewModel) {

        viewModel.deviceList.forEach {
            it.deviceBrightnessLevel = brightnessValue
        }
        viewModel.onDeviceSatusUpdate.postValue(true)
    }

    fun updateDeviceList(deviceId: Int, switchedOn: Boolean, viewModel: MainActivityViewModel) {

        viewModel.deviceList.forEach {
            if (it.deviceId == deviceId) {
                it.deviceStatus = switchedOn
            }
        }
        viewModel.onDeviceSatusUpdate.postValue(true)
    }

    fun updateDeviceList(switchedOn: Boolean, viewModel: MainActivityViewModel) {
        viewModel.deviceList.forEach {
            it.deviceStatus = switchedOn

        }
        viewModel.onDeviceSatusUpdate.postValue(true)

    }


}