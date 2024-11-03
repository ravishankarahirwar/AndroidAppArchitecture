package com.example.apparchitecture.presenter

import android.content.res.Resources
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apparchitecture.data.ApiResult
import com.example.apparchitecture.data.Item
import com.example.apparchitecture.data.Users
import com.example.apparchitecture.domain.ApiException
import com.example.apparchitecture.domain.FetchUserUseCase
import com.example.apparchitecture.domain.UnauthorizedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor( private val fetchUserUseCase: FetchUserUseCase) : ViewModel() {
    private val _users =
        MutableStateFlow<ApiResult<List<Users.UsersItem>>>(ApiResult.Success(emptyList()))
    val users: StateFlow<ApiResult<List<Users.UsersItem>>> get() = _users


    private var _userList = mutableStateListOf<Users.UsersItem>()
    val userList: List<Users.UsersItem> = _userList

    private val _items = mutableStateListOf<Item>()
    val items: List<Item> = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        // Simulating data fetching
        _items.addAll(
            listOf(
                Item(1, "Item 1"),
                Item(2, "Item 2"),
                Item(3, "Item 3")
            )
        )
    }


    fun fetchData() {
        _users.value = ApiResult.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = fetchUserUseCase()
                list.map {
                    _userList.add(it)
                }

            } catch (e: ApiException) {
                when (e.code) {
                    401 -> setNewValue(ApiResult.Failure(UnauthorizedException(401, "Unauthorized access")))
                    404 -> ApiResult.Failure(Resources.NotFoundException("Data not found"))
                    else -> ApiResult.Failure(e) // Handle other error codes
                }
            }
        }
    }

    fun setNewValue(userData: ApiResult<List<Users.UsersItem>>) {
        _users.value = userData
    }
}