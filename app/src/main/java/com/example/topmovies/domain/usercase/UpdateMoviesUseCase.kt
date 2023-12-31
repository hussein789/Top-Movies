package com.example.topmovies.domain.usercase

import com.example.topmovies.domain.repo.MovieRepository
import javax.inject.Inject

open class UpdateMoviesUseCase @Inject constructor(
        private val movieRepository: MovieRepository,
        private val updateGenresUseCase: UpdateGenresUseCase
) {
    suspend fun execute() {
        movieRepository.updateMovies()
        updateGenresUseCase.execute()
    }
}
