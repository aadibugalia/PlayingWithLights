package com.adityabugalia.playingwithlights.models

data class DeviceModel(
    var deviceId: Int,
    var deviceStatus: Int,
    var deviceBrightnessLevel: Int,
    var deviceColor: String,
    var deviceName: String
)