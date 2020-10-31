package com.surelabs.indonesia.koreancornersreaders.network

import com.surelabs.e.jsoupbotapps.model.ResponsePost
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("getPost/{limit}")
    suspend fun getPost(@Path("limit") limit: Int?): ResponsePost
}
