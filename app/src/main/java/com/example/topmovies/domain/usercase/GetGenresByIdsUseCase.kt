package com.example.topmovies.domain.usercase

import com.example.topmovies.domain.repo.MovieRepository
import javax.inject.Inject

open class GetGenresByIdsUseCase @Inject constructor(
        private val movieRepository: MovieRepository,
) {
    suspend fun execute(genreIds: List<Int>): List<String> {
        val genres = movieRepository.getGenres()
        return genres.filter { genreIds.contains(it.id) }.map { it.name }
    }
}
