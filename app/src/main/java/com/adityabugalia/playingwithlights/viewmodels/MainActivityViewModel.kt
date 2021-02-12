package com.adityabugalia.playingwithlights.viewmodels

import android.content.res.TypedArray
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adityabugalia.playingwithlights.devicecommunication.TransmitDataToDevice
import com.adityabugalia.playingwithlights.models.DeviceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivityViewModel : ViewModel() {

    var onDeviceSatusUpdate: MutableLiveData<Any> =
        MutableLiveData()
        private set
    var onViewReadyLiveData: MutableLiveData<Boolean> =
        MutableLiveData()
        private set
    var onMainDeviceTurnOff: MutableLiveData<Boolean> =
        MutableLiveData()
        private set
    var onMainBrightnessChange: MutableLiveData<Int> =
        MutableLiveData()
        private set

    lateinit var deviceList: MutableList<DeviceModel>
    private var totalBrightness = 0
    private var totalDevices = 0
    private var isAllDevicesOff = true
    private var isFirstTime = true
    val Boolean.int
        get() = if (this) 1 else 0


    fun onViewReady() {
        generateData()
    }

    private fun generateData() {
        var setCount = 0
        deviceList = ArrayList();
        var deviceId = 0
        var deviceStatus = 0
        var deviceBrightness = 0


        getInputArr().forEach {

            when (setCount) {
                0 -> {
                    deviceId = it.toInt()
                    setCount++
                }
                1 -> {
                    deviceStatus = it.toInt()
                    setCount++
                }
                2 -> {
                    deviceBrightness = it.toInt()
                    totalDevices++
                    updateTotalBrightness(deviceBrightness, true)
                    var deviceModel =
                        DeviceModel(
                            deviceId,
                            if (deviceStatus == 0) false else true, deviceBrightness, "#000000", ""
                        )
                    "?:>m,n"

                    deviceList.add(deviceModel)
                    updateAllDeviceStatus(deviceStatus)


                    deviceId = 0
                    deviceStatus = 0
                    deviceBrightness = 0
                    setCount = 0
                }
            }
        }
        sortDeviceList()
        transmitPrilimnaryData(isAllDevicesOff)

    }

    private fun updateAllDeviceStatus(deviceStatus: Int) {
        if (deviceStatus == 1)
            isAllDevicesOff = false

    }

    private fun getInputArr(): Array<String> {
        return "10001 1 34 10010 1 88 10004 1 20 10003 0 100 10007 0 5 10002 0 0 10008 0 41 10005 1 76 10006 0 52 10009 1 10".split(
            " "
        ).toTypedArray()
    }

    private fun transmitPrilimnaryData(isAllDevicesOff: Boolean) {
        onViewReadyLiveData.postValue(true)

        if (isAllDevicesOff)
            onMainDeviceTurnOff.postValue(true)

        onMainBrightnessChange.postValue(totalBrightness / totalDevices)
    }

    private fun sortDeviceList() {
        deviceList.sortBy { deviceModel -> deviceModel.deviceId }
    }


    private fun updateTotalBrightness(newValue: Int, isAddition: Boolean) {
        if (isAddition)
            totalBrightness = totalBrightness + newValue
        else
            totalBrightness = totalBrightness - newValue
    }


    fun onMainSwitchToggled(checked: Boolean) {
        Log.d("", "")
        TransmitDataToDevice.transmitDataForAllDevice(checked)
        updateDeviceList(checked)
    }

    fun onMainBrightnessToggled(brightnessValue: Int) {
        Log.d("Brightness", " Value: " + brightnessValue)
        if (!isFirstTime)
            updateDeviceList(brightnessValue)
        else
            isFirstTime = false
    }

    fun onDeviceSwitchToggled(deviceId: Int, switchedOn: Boolean) {
        Log.d("", "")
        TransmitDataToDevice.transmitDataForDevice(deviceId, switchedOn)
    }

    fun onDeviceBrightnessToggled(deviceId: Int, brightnessValue: Int) {
        Log.d("Brightness", "DeviceId:" + deviceId + " Value: " + brightnessValue)
        updateDeviceList(deviceId, brightnessValue)
    }

    fun updateDeviceList(deviceId: Int, brightnessValue: Int) {

        deviceList.forEach {
            if (it.deviceId == deviceId) {
                it.deviceBrightnessLevel = brightnessValue
            }
            totalBrightness = 0
            updateTotalBrightness(it.deviceBrightnessLevel, true)
        }
        onDeviceSatusUpdate.postValue(true)
        onMainBrightnessChange.postValue(totalBrightness)
    }

    fun updateDeviceList(brightnessValue: Int) {

        deviceList.forEach {
            it.deviceBrightnessLevel = brightnessValue
            totalBrightness = brightnessValue
        }
        onDeviceSatusUpdate.postValue(true)
    }

    fun updateDeviceList(deviceId: Int, switchedOn: Boolean) {

        deviceList.forEach {
            if (it.deviceId == deviceId) {

                it.deviceStatus = switchedOn
            }
        }
        onDeviceSatusUpdate.postValue(true)
    }

    fun updateDeviceList(switchedOn: Boolean) {
        deviceList.forEach {
            it.deviceStatus = switchedOn

        }
        onDeviceSatusUpdate.postValue(true)

    }

}
