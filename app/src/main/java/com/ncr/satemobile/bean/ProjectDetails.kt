package com.ncr.satemobile.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProjectDetails(
    val posPath: String? = null,
    val parentSetName: String? = null,
    val projectPath: String? = null,
    val posPathTarget: String? = null,
    val smtpserver: String? = null
):Parcelable
