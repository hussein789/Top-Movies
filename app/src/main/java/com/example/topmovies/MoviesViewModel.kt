package com.example.topmovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.data.model.Movie
import com.example.topmovies.domain.usercase.GetMoviesUseCase
import com.example.topmovies.domain.usercase.UpdateMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MovieCallState {
    LOADING, SUCCESS, ERROR
}

data class MovieUiState(
        val movies: List<Movie> = emptyList(),
        val movieCallState: MovieCallState = MovieCallState.LOADING,
)

@HiltViewModel
class MoviesViewModel @Inject constructor(
        private val getMoviesUseCase: GetMoviesUseCase,
        private val updateMoviesUseCase: UpdateMoviesUseCase,
) : ViewModel() {

    private val _movieUiState = MutableStateFlow(MovieUiState())
    val movieUiState: StateFlow<MovieUiState> = _movieUiState

    init {
        initData()
    }

    private fun initData() {
        viewModelScope.launch {
            getMoviesUseCase.execute().collect { movies ->
                _movieUiState.update { currentUiState ->
                    currentUiState.copy(movies = movies, movieCallState = MovieCallState.SUCCESS)
                }
            }
        }
        updateMovies()
    }

    fun updateMovies() {
       viewModelScope.launch {
           _movieUiState.update { current ->
               current.copy(movieCallState = MovieCallState.LOADING)
           }
            try {
                updateMoviesUseCase.execute()
                _movieUiState.update { current ->
                    current.copy(movieCallState = MovieCallState.SUCCESS)
                }
            } catch (ex: Exception) {
                _movieUiState.update { current ->
                    current.copy(movieCallState = MovieCallState.ERROR)
                }
                Log.d("hussein", "error happend while fetching data ${ex.message}")
            }
       }
    }
}
