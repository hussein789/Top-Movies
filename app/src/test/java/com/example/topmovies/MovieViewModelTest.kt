package com.example.topmovies

import com.example.topmovies.data.model.Genre
import com.example.topmovies.data.model.Movie
import com.example.topmovies.domain.usercase.GetGenresByIdsUseCase
import com.example.topmovies.domain.usercase.GetMoviesUseCase
import com.example.topmovies.domain.usercase.UpdateMoviesUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.utils.BaseUnitTest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class MovieViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var updateMoviesUseCase: UpdateMoviesUseCase
    private lateinit var getGenresByIdsUseCase: GetGenresByIdsUseCase

    private val movie1 = Movie(1, "overview 1", "poster 1", "release date 1", "title 1", 1.1, listOf(1, 2))
    private val movie2 = Movie(1, "overview 2", "poster 2", "release date 2", "title 2", 1.2, listOf(1, 2))
    private val currentMovies = listOf(movie1, movie2)

    private val genres = listOf(Genre(1, "Action"), Genre(2, "Crime"))

    @Before
    fun setup() {
        runBlocking {
             getMoviesUseCase = mock(GetMoviesUseCase::class.java)
             updateMoviesUseCase = mock(UpdateMoviesUseCase::class.java)
             getGenresByIdsUseCase = mock(GetGenresByIdsUseCase::class.java)

            whenever(getMoviesUseCase.execute()).thenReturn(flowOf(currentMovies))
            whenever(getGenresByIdsUseCase.execute(movie1.genreIds)).thenReturn(genres.map { it.name })

            viewModel = MoviesViewModel(getMoviesUseCase, updateMoviesUseCase, getGenresByIdsUseCase)
        }
    }

    @Test
    fun viewModelInit_haveDataLocally_returnSavedData() {
        val movies = viewModel.movieUiState.value.movies

        assertEquals(currentMovies, movies)
    }

    @Test
    fun updateMovies_verityUpdateMoviesUseCaseCalledOnce() = runBlockingTest {
        verify(updateMoviesUseCase).execute()
    }

    @Test
    fun onMovieSelected_updateStateAndGenres() {
        viewModel.onMovieSelected(movie1)

        assertEquals(movie1, viewModel.movieUiState.value.selectedMovie)
        assertEquals(genres.map { it.name }, viewModel.movieUiState.value.selectedMovieGenres)
    }
}
