package com.example.apparchitecture.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.apparchitecture.data.ApiResult
import com.example.apparchitecture.data.Users
import com.example.apparchitecture.domain.ApiException
import com.example.apparchitecture.presenter.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: UserViewModel by viewModels()
        setContent {
            AppArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showFilterSheet by remember { mutableStateOf(false) }
                    UserListScreen(viewModel)
//                    viewModel.fetchData()
                    lifecycleScope.launch(Dispatchers.Main) {
                        viewModel.users.collect { result ->
                            when (result) {
                                is ApiResult.Success -> {
                                    // Handle success case
                                   val  users = result.data
                                    Log.v("UserResponse" , ": ${users}")
                                    showFilterSheet = true
                                }
                                is ApiResult.Failure -> {
                                    // Handle failure case
                                    val exception = result.exception as ApiException
                                    Log.v("UserResponse" , ": ${exception.code}")
                                }
                                is ApiResult.Loading -> {

                                }

                            }
                        }
                    }

                    if(showFilterSheet) {
                        Greeting("Ravi")
                        UserListScreen(viewModel)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun UserListScreen( viewModel: UserViewModel) {
    viewModel.fetchData()
    val users = viewModel.userList

    LazyColumn {
        items(users) { item ->
            Text(
                text = item.address,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { /* Handle item click */ }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppArchitectureTheme {
        Greeting("Android")
    }
}