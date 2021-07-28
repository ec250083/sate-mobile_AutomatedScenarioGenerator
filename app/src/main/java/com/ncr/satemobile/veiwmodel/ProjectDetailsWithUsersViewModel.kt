package com.ncr.satemobile.veiwmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ncr.satemobile.api.ApiBuilder
import com.ncr.satemobile.bean.AssociatedProjectUser
import com.ncr.satemobile.bean.ProjectDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectDetailsWithUsersViewModel : ViewModel() {

    companion object {
        var apiservice = ApiBuilder.create()
    }

    var projectDetails: MutableLiveData<ProjectDetails>? = null
    var projectUsers: MutableLiveData<List<AssociatedProjectUser>>? = null
    fun getProjectDetails(projectName: String): MutableLiveData<ProjectDetails>? {
        if (projectDetails == null) {
            projectDetails = MutableLiveData()
            fetchProjectDetails(projectName)
        }
        return projectDetails
    }

    fun getProjectUsers(projectName: String): MutableLiveData<List<AssociatedProjectUser>>? {
        if (projectUsers == null) {
            projectUsers = MutableLiveData()
            fetProjectUsers(projectName)
        }
        return projectUsers
    }

    private fun fetchProjectDetails(projectName: String) {
        val projectDetailsCall = apiservice?.getProjectDetails(name = projectName)
        projectDetailsCall?.enqueue(object : Callback<ProjectDetails> {
            override fun onResponse(
                call: Call<ProjectDetails>,
                response: Response<ProjectDetails>
            ) {
                projectDetails?.value = response.body()
            }

            override fun onFailure(call: Call<ProjectDetails>, t: Throwable) {
            }

        })

    }

    private fun fetProjectUsers(projectName: String) {
        val prjusers = apiservice?.getProjectAssignedUsers(projectName)
        prjusers?.enqueue(object : Callback<List<AssociatedProjectUser>> {
            override fun onResponse(call: Call<List<AssociatedProjectUser>>, response: Response<List<AssociatedProjectUser>>) {
                projectUsers?.value = response.body()
            }

            override fun onFailure(call: Call<List<AssociatedProjectUser>>, t: Throwable) {
            }


        })

    }
}