package com.nemochen.hometimer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ElementDialogViewModel : ViewModel() {

    var editDateTrigger = MutableLiveData<Boolean>(null)
    var editTimeTrigger = MutableLiveData<Boolean>(null)

    private var timeInMillis = MutableLiveData(Long.MIN_VALUE)
    var calendar = MutableLiveData<Calendar?>()

    fun initCalendar() {
        calendar.value = Calendar.getInstance()
        updateTimeInMillis()
    }

    fun getTimeInMillis(): MutableLiveData<Long> {
        return timeInMillis
    }

    fun updateTimeInMillis() {
        calendar.value?.let {
            timeInMillis.value = it.timeInMillis
        }
    }

    fun editDate() {
        editDateTrigger.value = if (editDateTrigger.value == null) {
            true
        } else editDateTrigger.value?.not()
    }

    fun editTime() {
        editTimeTrigger.value = if (editTimeTrigger.value == null) {
            true
        } else editTimeTrigger.value?.not()
    }
}