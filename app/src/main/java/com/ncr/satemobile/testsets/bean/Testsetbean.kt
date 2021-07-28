package com.ncr.satemobile.testsets.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Testsetbean(

	@field:SerializedName("tsName")
	val tsName: String? = null,

	@field:SerializedName("tsCreateBy")
	val tsCreateBy: String? = null,

	@field:SerializedName("tsDesc")
	val tsDesc: String? = null
) : Parcelable
