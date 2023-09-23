package com.example.topmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.topmovies.databinding.ActivityMainBinding
import com.example.topmovies.presentation.movies.MovieDetailsScreen
import com.example.topmovies.presentation.movies.MoviesScreen
import com.example.topmovies.ui.theme.TopMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

enum class MovieDestination() {
    LIST, DETAILS
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopMoviesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TopMovieApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun TopMovieApp(
        moviesViewModel: MoviesViewModel = viewModel(),
        navController: NavHostController = rememberNavController(),
        modifier: Modifier = Modifier,

) {
    val currentScreen = navController.currentBackStackEntryAsState()
    Scaffold(
            topBar = {
                TopMoviesTopBar(currentScreen.value?.destination?.route ?: "", navController.previousBackStackEntry != null) { navController.navigateUp() }
            },
    ) { contentPadding ->
        val uiState by moviesViewModel.movieUiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = MovieDestination.LIST.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            composable(route = MovieDestination.LIST.name) {
                MoviesScreen(movies = uiState.movies, uiState.movieCallState, onMovieClicked = {
                    moviesViewModel.onMovieSelected(it)
                    navController.navigate(MovieDestination.DETAILS.name)
                })
            }

            composable(route = MovieDestination.DETAILS.name) {
                MovieDetailsScreen(movie = uiState.selectedMovie, genres = uiState.selectedMovieGenres, onBackPressed = { navController.navigateUp() })
            }
        }
    }
}

@Composable
fun TopMoviesTopBar(
        title: String,
        shouldShowBackButton: Boolean,
        onBackButtonPressed: () -> Unit,
) {
    TopAppBar(title = {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            if (shouldShowBackButton) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = null, modifier = Modifier.clickable { onBackButtonPressed() })
            }
            Text(text = stringResource(id = R.string.app_name), modifier = Modifier.weight(1f), textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineLarge)
        }
    })
}
