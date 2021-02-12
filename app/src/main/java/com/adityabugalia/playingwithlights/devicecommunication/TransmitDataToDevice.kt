package com.adityabugalia.playingwithlights.devicecommunication

import android.util.Log

object TransmitDataToDevice {

    fun transmitDataForDevice(deviceId:Int, switchedOn: Boolean ){

        if(switchedOn)
            Log.d("","Device :" + deviceId+ " is Turned On")
        else
            Log.d("","Device :" + deviceId+ " is Turned OFF")

    }
    fun transmitDataForAllDevice(switchedOn: Boolean ){

        if(switchedOn)
            Log.d("","All Devices are Turned On")
        else
            Log.d("","All Devices are Turned On")

    }
}