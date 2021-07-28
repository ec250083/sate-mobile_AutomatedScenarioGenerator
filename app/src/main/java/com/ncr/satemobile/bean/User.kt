package com.ncr.satemobile.bean

import java.io.Serializable

class User(val name:String?=null,val email:String?=null,val responseCode:Int?=null,val currentProject:String?=null) :
    Serializable