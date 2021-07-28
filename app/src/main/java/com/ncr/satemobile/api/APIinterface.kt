package com.ncr.satemobile.api

import com.ncr.satemobile.actions.Actions
import com.ncr.satemobile.bean.AssociatedProjectUser
import com.ncr.satemobile.bean.ProjectBean
import com.ncr.satemobile.bean.ProjectDetails
import com.ncr.satemobile.bean.User
import com.ncr.satemobile.scenarios.bean.ScenarioBean
import com.ncr.satemobile.testsets.bean.Testsetbean
import retrofit2.http.POST
import retrofit2.http.Query

interface APIinterface {
    @POST("/finduser")
    fun findUserAndLogin(@Query("name") name: String? = null): retrofit2.Call<User>

    @POST("/getProjectsByUser")
    fun getUserProjects(@Query("name") name: String? = null): retrofit2.Call<List<ProjectBean>>


    @POST("/getProjectDetails")
    fun getProjectDetails(@Query("name") name: String? = null): retrofit2.Call<ProjectDetails>

    @POST("/assignedUsers")
    fun getProjectAssignedUsers(@Query("name") name: String? = null): retrofit2.Call<List<AssociatedProjectUser>>

    @POST("/getAllScennariosByProject")
    fun getAllScennariosByProject(
        @Query("name") name: String? = null,
        @Query("num") num: Int? = 10
    ): retrofit2.Call<List<ScenarioBean>>

    @POST("/getAllactionsByProject")
    fun getAllactionsByProject(
        @Query("name") name: String? = null,
        @Query("num") num: Int? = 10
    ): retrofit2.Call<List<Actions>>

    @POST("/getallTesetsByProject")
    fun getallTesetsByProject(
        @Query("name") name: String? = null,
        @Query("num") num: Int? = 10
    ): retrofit2.Call<List<Testsetbean>>

}