package com.nemochen.hometimer.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemochen.hometimer.model.CountdownElement
import com.nemochen.hometimer.model.DataModel
import com.nemochen.hometimer.util.SingleLiveEvent

class MainViewModel : ViewModel() {

    companion object {
        const val DOWNLOAD_COMPLETE = "Download completed"
    }

    var isLoading = MutableLiveData(false)
    var toastMessage = SingleLiveEvent<String>()
    var itemList = MutableLiveData<MutableList<CountdownElement>>()
    var displayString = MutableLiveData<String>()

    var dataModel = DataModel()

    fun getData() {
        isLoading.value = true
        dataModel.retrieveData(object : DataModel.onDataReadyCallback{
            override fun onDataReady(dataList: List<CountdownElement>) {
                itemList.value = dataList as MutableList<CountdownElement>
                displayString.value = itemList.value?.get(0)?.name
                toastMessage.value = DOWNLOAD_COMPLETE
                isLoading.value = false
            }
        })
    }

    // TODO: Implement the ViewModel
}
