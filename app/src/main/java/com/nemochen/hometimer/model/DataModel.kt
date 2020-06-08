package com.nemochen.hometimer.model

class DataModel {
    var mutableList: MutableList<CountdownElement>  = mutableListOf()
    init {
        mutableList.add(CountdownElement("Water Filter", 1591747200000))   //2020年6月10日星期三 08:00:00 GMT+08:00
        mutableList.add(CountdownElement("Bath Water Filter", 1591761600000))   //2020年6月10日星期三 12:00:00 GMT+08:00
        mutableList.add(CountdownElement("Toothbrush", 1591624800000))   //2020年6月8日星期一 22:00:00 GMT+08:00
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