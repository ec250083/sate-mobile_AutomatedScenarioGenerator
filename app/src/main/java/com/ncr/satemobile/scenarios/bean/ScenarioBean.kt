package com.ncr.satemobile.scenarios.bean

import com.google.gson.annotations.SerializedName

data class ScenarioBean(

	@field:SerializedName("scenarioDesc")
	val scenarioDesc: String? = null,

	@field:SerializedName("scenarioType")
	val scenarioType: String? = null,

	@field:SerializedName("scenarioName")
	val scenarioName: String? = null
)
