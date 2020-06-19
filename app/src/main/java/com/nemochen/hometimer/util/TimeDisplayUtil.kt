package com.nemochen.hometimer.util

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class TimeDisplayUtil {
    companion object {
        const val PADDING_CHAR = '0'

        val dataFormater = SimpleDateFormat("yyyy/MM/dd")
        val timeFormater = SimpleDateFormat("HH:mm:ss")
        val timeFormaterWithoutSecond = SimpleDateFormat("HH:mm")
        val dataTimeFormater = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        fun getDisplayString(remainingSeconds: Long): String {
            var rDays = TimeUnit.SECONDS.toDays(remainingSeconds)
            var rHours = TimeUnit.SECONDS.toHours(remainingSeconds % TimeUnit.DAYS.toSeconds(1)).toString().padStart(2, PADDING_CHAR)
            var rMinutes = TimeUnit.SECONDS.toMinutes(remainingSeconds % TimeUnit.HOURS.toSeconds(1)).toString().padStart(2, PADDING_CHAR)
            var rSeconds = TimeUnit.SECONDS.toSeconds(remainingSeconds % TimeUnit.MINUTES.toSeconds(1)).toString().padStart(2, PADDING_CHAR)

            var displayString = "$rHours:$rMinutes:$rSeconds"
            if (rDays != null && rDays != 0L) {
                displayString = "$rDays Days, $displayString"
            }
            return displayString
        }
    }

}