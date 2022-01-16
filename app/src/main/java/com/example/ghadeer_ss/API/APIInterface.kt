package com.example.ghadeer_ss.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("/search")
    fun getUniversityData(@Query("name") text:String): Call<UniversityData>
}
