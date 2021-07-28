package com.ncr.satemobile

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
fun Context.log(logmsg:String)
{
    println("Logger - $logmsg")
}

fun log(logmsg:String)
{
    println("Logger - $logmsg")
}

fun a()
{

}