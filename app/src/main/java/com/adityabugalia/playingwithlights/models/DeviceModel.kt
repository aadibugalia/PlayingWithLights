package com.adityabugalia.playingwithlights.models

data class DeviceModel(
    var deviceId: Int,
    var deviceStatus: Boolean,
    var deviceBrightnessLevel: Int,
    var deviceColor: String,
    var deviceName: String
)