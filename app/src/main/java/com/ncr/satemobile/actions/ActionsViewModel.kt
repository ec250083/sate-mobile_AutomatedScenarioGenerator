package com.ncr.satemobile.actions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ncr.satemobile.api.ApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActionsViewModel : ViewModel() {
    companion object {
        var apiservice = ApiBuilder.create()
    }
    var actions: MutableLiveData<List<Actions>>? = null

    fun getActions(name:String): MutableLiveData<List<Actions>>
    {

        if (actions==null)
        {
            actions= MutableLiveData<List<Actions>>()
            getActionsApiCall(name)

        }
        return actions as MutableLiveData<List<Actions>>
    }

    private fun getActionsApiCall(name: String) {
        val scenariosApi= apiservice?.getAllactionsByProject(name)
        scenariosApi?.enqueue(object : Callback<List<Actions>>
        {
            override fun onResponse(
                call: Call<List<Actions>>,
                response: Response<List<Actions>>
            ) {
                actions?.value=response.body()
            }

            override fun onFailure(call: Call<List<Actions>>, t: Throwable) {

            }

        })
    }
}