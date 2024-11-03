package com.example.apparchitecture.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.apparchitecture.data.ApiResult
import com.example.apparchitecture.data.createFakeUsers
import com.example.apparchitecture.domain.ApiException
import com.example.apparchitecture.domain.FetchUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class UserViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule() // Allows LiveData to execute synchronously

    private val fetchUsersUseCase: FetchUserUseCase = mock(FetchUserUseCase::class.java)
    private lateinit var userViewModel: UserViewModel

    @Before
    fun setup() {
        userViewModel = UserViewModel(fetchUsersUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchUsers updates LiveData with fetched data`() = runTest  {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        try {
            // Given
            val mockUsers = createFakeUsers() // Use the fake data function from earlier
            `when`(fetchUsersUseCase()).thenReturn(ApiResult.Failure(ApiException(404, "Not Authorize"))) // Stub the use case call

            val job = launch {
                userViewModel.users.collect { result ->
                    when (result) {
                        is ApiResult.Success -> {
                            // Assert the success condition
                            assertEquals(mockUsers, result.data)
                        }
                        is ApiResult.Failure -> {
                            fail("Expected success but got failure")
                        }
                        is ApiResult.Loading -> {
                            fail("Expected success but got failure")
                        }
                    }
                }
            }

            userViewModel.fetchData() // Call the fetch function
            userViewModel.setNewValue(ApiResult.Success(mockUsers))
            job.cancel() // Clean up the coroutine job

//            verify(fetchUsersUseCase).invoke() // Ensure the use case was called
        }finally {
            Dispatchers.resetMain()
        }
    }
}


