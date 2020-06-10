package com.nemochen.hometimer.model

class DataModel {
    var mutableList: MutableList<CountdownElement>  = mutableListOf()
    init {
        mutableList.add(CountdownElement("Water Filter", System.currentTimeMillis() + 86415 * 1000))
        mutableList.add(CountdownElement("Bath Water Filter", System.currentTimeMillis() + 3600 * 5 * 1000))
        mutableList.add(CountdownElement("Toothbrush", System.currentTimeMillis() + 2407 * 1000))
        mutableList.add(CountdownElement("除濕劑", System.currentTimeMillis() + 30 * 1000))
    }

    fun retrieveData(dataReadyCallback: onDataReadyCallback) {
        android.os.Handler().postDelayed({
            dataReadyCallback.onDataReady(mutableList)
        }, 2000)
    }

    interface onDataReadyCallback {
        fun onDataReady(dataList: List<CountdownElement>)
    }

}