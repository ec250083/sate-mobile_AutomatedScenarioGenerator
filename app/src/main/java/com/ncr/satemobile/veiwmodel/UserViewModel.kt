package com.ncr.satemobile.veiwmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ncr.satemobile.api.ApiBuilder
import com.ncr.satemobile.bean.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    companion object {
        var apiservice = ApiBuilder.create()
    }

    var user: MutableLiveData<User>? = null
    fun getLoginDetails(name: String): LiveData<User> {
        if (user == null) {
            user = MutableLiveData<User>()
            getUser(name)
        }
        return user as MutableLiveData<User>
    }

    private fun getUser(name: String) {
        val finduser = apiservice?.findUserAndLogin(name)
        finduser?.enqueue(object :Callback<User>
        {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                 if (response.isSuccessful)
                 {
                     if(response.code()==200)
                     {
                         user?.value=response.body()
                     }
                 }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

             }

        })





   /*     finduser?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.code()==200)
                {
                   user?.value=response.body()
                }
                else
                {
                    //user?.value=response.
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println("failure "+t.localizedMessage)
            }

        })*/

    }
}