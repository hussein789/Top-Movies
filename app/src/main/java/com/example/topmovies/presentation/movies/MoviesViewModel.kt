package com.example.topmovies.presentation.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.data.model.Movie
import com.example.topmovies.domain.usercase.GetMoviesUseCase
import com.example.topmovies.domain.usercase.UpdateMoviesUseCase
import kotlinx.coroutines.launch

class MoviesViewModel(
        private val getMoviesUseCase: GetMoviesUseCase,
        private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    private val _moviesList = getMoviesUseCase.execute()
    val moviesList: LiveData<List<Movie>> = _moviesList

    fun updateMovies() {
       viewModelScope.launch {
            try {
                updateMoviesUseCase.execute()
            } catch (ex: Exception) {
                Log.d("hussein", "error happend while fetching data ${ex.message}")
            }
       }
    }
}
