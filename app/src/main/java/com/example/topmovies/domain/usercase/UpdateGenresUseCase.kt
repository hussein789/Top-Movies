package com.example.topmovies.domain.usercase

import com.example.topmovies.domain.repo.MovieRepository
import javax.inject.Inject

open class UpdateGenresUseCase @Inject constructor(
        private val movieRepository: MovieRepository,
) {
    suspend fun execute() {
        movieRepository.updateGenres()
    }
}
