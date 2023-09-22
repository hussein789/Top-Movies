package com.example.topmovies.domain.usercase

import com.example.topmovies.domain.repo.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
        private val movieRepository: MovieRepository,
) {
    fun execute() = movieRepository.getMovies()
}
