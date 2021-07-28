package com.ncr.satemobile.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ncr.satemobile.api.ApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    companion object
    {
        val chartapi=ApiBuilder.createChart()
    }

    var chartinfo:MutableLiveData<List<ChartResponse>>?=null
    var cycleChartinfo:MutableLiveData<List<ChartResponse>>?=null

    fun getchartData():MutableLiveData<List<ChartResponse>>
    {
        if (chartinfo==null)
        {
            chartinfo= MutableLiveData()
            getChartInfo()
        }
        return chartinfo as MutableLiveData<List<ChartResponse>>
    }

    fun getCycleGraphData():MutableLiveData<List<ChartResponse>>
    {
        if (cycleChartinfo==null)
        {
            cycleChartinfo= MutableLiveData()
            getCycleGraphInfo()
        }
        return cycleChartinfo as MutableLiveData<List<ChartResponse>>
    }

    private fun getChartInfo() {
         val chartapiCall= chartapi?.getChartInfo()
        chartapiCall?.enqueue(object :Callback<List<ChartResponse>>
        {
            override fun onResponse(call: Call<List<ChartResponse>>, response: Response<List<ChartResponse>>) {
                 chartinfo?.value=response.body()
            }

            override fun onFailure(call: Call<List<ChartResponse>>, t: Throwable) {
                println("Chart error")
             }

        })
    }

    private fun getCycleGraphInfo() {
         val chartapiCall= chartapi?.getCycleGraph()
        chartapiCall?.enqueue(object :Callback<List<ChartResponse>>
        {
            override fun onResponse(call: Call<List<ChartResponse>>, response: Response<List<ChartResponse>>) {
                cycleChartinfo?.value=response.body()
            }

            override fun onFailure(call: Call<List<ChartResponse>>, t: Throwable) {
                println("Chart error")
             }

        })
    }

}