package com.example.audiobookscomapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.audiobookscomapplication.data.PodcastRepository
import com.example.audiobookscomapplication.ui.theme.AudioBookscomApplicationTheme
import com.example.audiobookscomapplication.views.HomepageView
import com.example.audiobookscomapplication.views.PodcastDetailsPageView
import com.example.audiobookscomapplication.viewmodels.HomePageViewModel
import com.example.audiobookscomapplication.viewmodels.PodcastDetailsPageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        CoroutineScope(Dispatchers.IO).launch {
            PodcastRepository.init(application)
        }
        setContent {
            AudioBookscomApplicationTheme {
                val navController = rememberNavController()
                val homeViewModel = ViewModelProvider(this)[HomePageViewModel::class]

                NavHost(navController = navController, startDestination = Screen.Main.route) {
                    composable(route = Screen.Main.route) {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            HomepageView(
                                navController = navController,
                                viewModel = homeViewModel,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                    composable(
                        route = Screen.Detail.route + "?id={id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.StringType
                                nullable = true
                            }
                        )
                    ) {
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    navigationIcon = {
                                        Row(
                                            Modifier
                                                .padding(start = 8.dp)
                                                .clickable { navController.popBackStack() }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Localized description"
                                            )
                                            Text("Back", modifier = Modifier.padding(start = 4.dp))
                                        }
                                    },
                                    title = {},
                                )
                            },
                        ) { innerPadding ->
                            PodcastDetailsPageView(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = PodcastDetailsPageViewModel(
                                    id = it.arguments?.getString("id")
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Detail : Screen("detail_screen")
}


