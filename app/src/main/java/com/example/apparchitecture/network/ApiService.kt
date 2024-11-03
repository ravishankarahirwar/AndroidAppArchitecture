package com.example.apparchitecture.network

import com.example.apparchitecture.data.Users
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users") // Replace with your actual API endpoint
    suspend fun fetchUser(): Response<Users> // Adjust the return type based on your API response

}