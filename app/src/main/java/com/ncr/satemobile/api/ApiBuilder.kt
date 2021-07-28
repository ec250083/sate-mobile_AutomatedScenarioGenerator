package com.ncr.satemobile.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiBuilder {

       var  BASE_URL = "http://10.0.2.2:8090"
       var  BASE_URL_CHART = "http://153.77.165.23:3232"


    var retrofit: Retrofit? = null
    var retrofit_chart: Retrofit? = null

    fun reBaseTheEndPointUrl(url: String): Unit {
        BASE_URL = url
        retrofit.let {
            retrofit = null
            create()
        }

    }

    /**
     * configure the Builder for API calls
     */
    fun create(): APIinterface? {

        if (BASE_URL.contains("http://", true)) {
            BASE_URL.let {

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit?.create(APIinterface::class.java)!!
            }
        } else {
            return null
        }

    }
    fun createChart(): chartapi? {

        if (BASE_URL_CHART.contains("http://", true)) {
            BASE_URL_CHART.let {

                retrofit_chart = Retrofit.Builder()
                    .baseUrl(BASE_URL_CHART)
                    .client(client1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit_chart?.create(chartapi::class.java)!!
            }
        } else {
            return null
        }

    }
    /**
     * logging
     */
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client1: OkHttpClient = OkHttpClient.Builder().apply {
        this.readTimeout(20, TimeUnit.SECONDS)
        this.connectTimeout(10, TimeUnit.SECONDS)
        this.writeTimeout(20, TimeUnit.SECONDS)
      //  this.addInterceptor(BasicAuthInterceptor())// header authentication
        this.addInterceptor(interceptor)


    }.build()

    //}


}
