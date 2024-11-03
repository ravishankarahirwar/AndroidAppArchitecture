package com.example.apparchitecture.data

import com.example.apparchitecture.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchUser(): Response<Users> {
        return apiService.fetchUser()
    }
}

//        return  if (response.isSuccessful) {
//            val responseBody = response.body() ?: throw Exception("Empty response")
//           ApiResult.Success(responseBody)
//
//        } else {
//            throw ApiException(response.code(), response.message())
//        }
//
//    }
//}
//class ApiException(val code: Int, message: String) : Exception(message)
