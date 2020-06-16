package com.nemochen.hometimer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ElementDialogViewModel : ViewModel() {

    var editDateTrigger = MutableLiveData<Boolean>(null)
    var editTimeTrigger = MutableLiveData<Boolean>(null)


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