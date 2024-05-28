package com.mitkod.kotlinsingletonexample.api

import com.mitkod.kotlinsingletonexample.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User
}