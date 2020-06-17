package com.nemochen.hometimer.util

import java.text.SimpleDateFormat

class TimeDisplayUtil {
    companion object {
        const val MINUTE_SECONDS = 60
        const val HOUR_SECONDS = MINUTE_SECONDS * 60
        const val DAY_SECONDS = HOUR_SECONDS * 24
        const val PADDING_CHAR = '0'

        val dataFormater = SimpleDateFormat("yyyy/MM/dd")
        val dataTimeFormater = SimpleDateFormat("yyyy/mm/dd HH:mm:ss")

        fun getDisplayString(remainingSeconds: Long): String {
            var rDays = if (remainingSeconds >= DAY_SECONDS) {remainingSeconds / DAY_SECONDS} else null
            var rHours = if (rDays != null) {
                ((remainingSeconds % DAY_SECONDS) / HOUR_SECONDS).toString().padStart(2, PADDING_CHAR)
            } else {
                (remainingSeconds / HOUR_SECONDS).toString().padStart(2, PADDING_CHAR)
            }
            var rMinutes = ((remainingSeconds % HOUR_SECONDS) / MINUTE_SECONDS).toString().padStart(2, PADDING_CHAR)
            var rSeconds = (remainingSeconds % MINUTE_SECONDS).toString().padStart(2, PADDING_CHAR)

            var displayString = "$rHours:$rMinutes:$rSeconds"
            if (rDays != null) {
                displayString = "$rDays Days, $displayString"
            }
            return displayString
        }
    }

}