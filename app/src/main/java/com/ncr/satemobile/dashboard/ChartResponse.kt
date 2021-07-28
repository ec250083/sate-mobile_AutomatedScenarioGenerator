package com.ncr.satemobile.dashboard

import com.google.gson.annotations.SerializedName

data class ChartResponse(

    @SerializedName("ChartName") var ChartName: String,
    @SerializedName("ChartLabels") var ChartLabels: String,
    @SerializedName("ChartData") var ChartData: String,
    @SerializedName("Passrate")
    var Passrate: String? = null,
    @SerializedName("TotalScenario")
    var TotalScenario: String? = null,
    @SerializedName("PassScenario")
    var PassScenario: String? = null

)
