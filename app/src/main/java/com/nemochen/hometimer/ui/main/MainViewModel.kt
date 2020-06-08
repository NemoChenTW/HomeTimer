package com.nemochen.hometimer.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.nemochen.hometimer.model.CountdownElement
import com.nemochen.hometimer.model.DataModel

class MainViewModel : ViewModel() {

    var itemList = ObservableField<MutableList<CountdownElement>>()
    var displayString = ObservableField<String>()

    var dataModel = DataModel()

    fun getData() {
        println("getData")
        dataModel.retrieveData(object : DataModel.onDataReadyCallback{
            override fun onDataReady(dataList: List<CountdownElement>) {
                itemList.set(dataList as MutableList<CountdownElement>)
                displayString.set(itemList.get()?.get(0)?.name)
            }
        })
    }

    // TODO: Implement the ViewModel
}
