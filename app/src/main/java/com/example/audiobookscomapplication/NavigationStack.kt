// NavigationStack.kt
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.audiobookscomapplication.Screen
import com.example.audiobookscomapplication.view.HomepageView
import com.example.audiobookscomapplication.view.PodcastDetailsPageView
import com.example.audiobookscomapplication.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    val homeViewModel = HomeViewModel()

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
                            Row (Modifier.padding(start = 8.dp).clickable { navController.popBackStack() }){
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
                PodcastDetailsPageView(id = it.arguments?.getString("id"), modifier = Modifier.padding(innerPadding))
            }
        }
    }
}