package com.ncr.satemobile.veiwmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ncr.satemobile.api.ApiBuilder
import com.ncr.satemobile.bean.ProjectBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectsViewModel: ViewModel() {
    companion object {
        var apiservice = ApiBuilder.create()
    }

    var projects: MutableLiveData<List<ProjectBean>>? = null
    fun getProjectsByUser(name: String): LiveData<List<ProjectBean>> {
        if (projects == null) {
            projects = MutableLiveData<List<ProjectBean>>()
            getAssociatedProjects(name)
        }
        return projects as MutableLiveData<List<ProjectBean>>
    }

    private fun getAssociatedProjects(name: String) {
        val projectCall = apiservice?.getUserProjects(name)

        projectCall?.enqueue(object : Callback<List<ProjectBean>> {
            override fun onResponse(call: Call<List<ProjectBean>>, response: Response<List<ProjectBean>>) {

                if(response.isSuccessful && response.code()==200)
                {
                    projects?.value=response.body()
                }
            }


            override fun onFailure(call: Call<List<ProjectBean>>, t: Throwable) {
            }

        })
    }
}
