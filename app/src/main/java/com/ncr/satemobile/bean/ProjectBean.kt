package com.ncr.satemobile.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProjectBean(var currentProject:String?=null,var projectName:String?=null,var id:Int?=null,var projectPath:String?=null) :
    Parcelable