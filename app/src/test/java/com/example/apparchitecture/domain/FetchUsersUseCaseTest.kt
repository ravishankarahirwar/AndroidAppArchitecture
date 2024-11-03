package com.example.apparchitecture.domain

import com.example.apparchitecture.data.ApiResult
import com.example.apparchitecture.data.UserRepository
import com.example.apparchitecture.data.createFakeUsers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

class FetchUsersUseCaseTest {

    private val userRepository: UserRepository = mock(UserRepository::class.java)
    private val fetchUsersUseCase = FetchUserUseCase(userRepository)

    @Test
    fun `invoke returns data from user repository`() = runTest {
        // Given
        val mockUsers = createFakeUsers() // Use the fake data function from earlier
        `when`(userRepository.fetchUser()).thenReturn(Response.success(mockUsers)) // Stub the repository call

        // When
        val result = fetchUsersUseCase() // Call the use case

        val successMockUsers = ApiResult.Success(mockUsers)

        // Then
        assertEquals(successMockUsers, result) // Verify the result is as expected
        verify(userRepository).fetchUser() // Ensure the repository method was called
    }
}
