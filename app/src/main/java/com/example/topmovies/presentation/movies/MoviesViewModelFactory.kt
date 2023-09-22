package com.example.topmovies.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.topmovies.domain.usercase.GetMoviesUseCase
import com.example.topmovies.domain.usercase.UpdateMoviesUseCase
import javax.inject.Inject

class MoviesViewModelFactory @Inject constructor(
        private val getMoviesUseCase: GetMoviesUseCase,
        private val updateMoviesUseCase: UpdateMoviesUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(getMoviesUseCase, updateMoviesUseCase) as T
        }
        throw IllegalArgumentException("unknown viewmodel found")
    }
}
