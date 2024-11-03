package com.example.apparchitecture.domain

import android.annotation.SuppressLint
import com.example.apparchitecture.data.ApiResult
import com.example.apparchitecture.data.Users
import com.example.apparchitecture.data.UserRepository
import javax.inject.Inject

class FetchUserUseCase @Inject constructor(private val repository: UserRepository) {
    @SuppressLint("SuspiciousIndentation")
        suspend operator fun invoke(): List<Users.UsersItem> {
        val usersResponse = repository.fetchUser()
        return if (usersResponse.isSuccessful) {
        val userList =  usersResponse.body() ?: throw Exception("Empty response")
            userList
        } else {
           emptyList()
        }
    }
}
// Custom exception
class UnauthorizedException(val code: Int, message: String) : Exception(message)
class ApiException(val code: Int, message: String) : Exception(message)
