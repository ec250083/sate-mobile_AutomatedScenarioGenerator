package com.ncr.satemobile.scenarios

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ncr.satemobile.api.ApiBuilder
import com.ncr.satemobile.scenarios.bean.ScenarioBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScenarioViewModel : ViewModel() {

    companion object {
        var apiservice = ApiBuilder.create()
    }
    var scenarios: MutableLiveData<List<ScenarioBean>>? = null

    fun getScenarios(name:String):MutableLiveData<List<ScenarioBean>>
    {

        if (scenarios==null)
        {
            scenarios= MutableLiveData<List<ScenarioBean>>()
            getScenarioApiCall(name)

        }
        return scenarios as MutableLiveData<List<ScenarioBean>>
    }

    private fun getScenarioApiCall(name: String) {
         val scenariosApi= apiservice?.getAllScennariosByProject(name)
        scenariosApi?.enqueue(object : Callback<List<ScenarioBean>>
        {
            override fun onResponse(
                call: Call<List<ScenarioBean>>,
                response: Response<List<ScenarioBean>>
            ) {
                 scenarios?.value=response.body()
            }

            override fun onFailure(call: Call<List<ScenarioBean>>, t: Throwable) {

            }

        })
    }

}