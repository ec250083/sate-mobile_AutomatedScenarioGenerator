package com.ncr.satemobile.dashboard

import com.google.gson.annotations.SerializedName

data class CycleGraphResponse(

	@field:SerializedName("CycleGraphResponse")
	val cycleGraphResponse: List<CycleGraphResponseItem?>? = null
)

data class CycleGraphResponseItem(

	@field:SerializedName("ChartLabels")
	val chartLabels: String? = null,

	@field:SerializedName("ChartData")
	val chartData: String? = null,

	@field:SerializedName("ChartName")
	val chartName: String? = null
)
