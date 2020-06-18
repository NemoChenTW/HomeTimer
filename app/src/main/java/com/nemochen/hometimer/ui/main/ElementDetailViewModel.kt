package com.nemochen.hometimer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemochen.hometimer.model.CountdownElement

class ElementDetailViewModel : ViewModel() {
    val changedElement = MutableLiveData<CountdownElement>()

    fun modify(countdownElement: CountdownElement) {
        changedElement.value = countdownElement
    }


}