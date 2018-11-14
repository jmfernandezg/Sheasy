package de.jensklingenberg.sheasy.legacy.utils

import android.os.Build
import model.DeviceResponse

/**
 * Created by jens on 25/2/18.
 */
class DeviceUtils {
    companion object {
        fun getDeviceInfo(): DeviceResponse {

            val device = DeviceResponse(
                Build.MANUFACTURER,
                Build.MODEL, DiskUtils.busySpace(true),
                DiskUtils.totalSpace(true),
                DiskUtils.freeSpace(true),
                Build.VERSION.RELEASE
            )

            return device
        }
    }


}