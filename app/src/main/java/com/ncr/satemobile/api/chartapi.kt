package com.ncr.satemobile.api

import com.ncr.satemobile.dashboard.ChartResponse
import retrofit2.http.GET

interface chartapi {

    @GET("/home")
    fun getChartInfo(): retrofit2.Call<List<ChartResponse>>

    @GET("/project")
    fun getCycleGraph():retrofit2.Call<List<ChartResponse>>
}