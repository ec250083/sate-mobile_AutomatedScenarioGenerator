package com.ncr.satemobile.testsets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ncr.satemobile.api.ApiBuilder
import com.ncr.satemobile.testsets.bean.Testsetbean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestsetViewModel : ViewModel() {
    companion object {
        var apiservice = ApiBuilder.create()
    }

    var scenarios: MutableLiveData<List<Testsetbean>>? = null

    fun getTestsets(name: String): MutableLiveData<List<Testsetbean>> {

        if (scenarios == null) {
            scenarios = MutableLiveData<List<Testsetbean>>()
            getTestsetApiCall(name)

        }
        return scenarios as MutableLiveData<List<Testsetbean>>
    }

    private fun getTestsetApiCall(name: String) {
        val scenariosApi = apiservice?.getallTesetsByProject(name)
        scenariosApi?.enqueue(object : Callback<List<Testsetbean>> {
            override fun onResponse(
                call: Call<List<Testsetbean>>,
                response: Response<List<Testsetbean>>
            ) {
                scenarios?.value = response.body()
            }

            override fun onFailure(call: Call<List<Testsetbean>>, t: Throwable) {

            }

        })
    }
}