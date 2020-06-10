package com.nemochen.hometimer.model

import android.os.CountDownTimer

data class CountdownElement(val name: String, val endTime: Long) {
    var countDownTimer: CountDownTimer? = null
    var displayTimeString: String = ""
}