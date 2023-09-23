package com.example.topmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.topmovies.databinding.ActivityMainBinding
import com.example.topmovies.presentation.movies.MoviesScreen
import com.example.topmovies.ui.theme.TopMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopMoviesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TopMovieApp()
                }
            }
        }
    }
}

@Composable
fun TopMovieApp(
        moviesViewModel: MoviesViewModel = viewModel(),
        navController: NavHostController = rememberNavController(),

) {
    Scaffold(
            topBar = { TopMoviesTopBar() },
    ) { contentPadding ->
        val uiState by moviesViewModel.movieUiState.collectAsState()
        MoviesScreen(movies = uiState.movies, uiState.movieCallState, onMovieClicked = {}, Modifier.padding(contentPadding))
    }
}

@Composable
fun TopMoviesTopBar() {
    CenterAlignedTopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.displaySmall)
    })
}
