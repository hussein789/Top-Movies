package com.example.topmovies.domain.usercase

import com.example.topmovies.data.model.Genre
import com.example.topmovies.domain.repo.MovieRepository
import com.nhaarman.mockitokotlin2.whenever
import com.utils.BaseUnitTest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class GetGenresByIdsUseCaseTest : BaseUnitTest() {
    private lateinit var useCase: GetGenresByIdsUseCase
    private val movieRepository = mock(MovieRepository::class.java)

    private val genres = listOf(Genre(1, "Action"), Genre(2, "Crime"), Genre(3, "Funny"))

    @Before
    fun setup() = runBlocking {
        whenever(movieRepository.getGenres()).thenReturn(genres)
        useCase = GetGenresByIdsUseCase(movieRepository)
    }

    @Test
    fun execute_exitsList_returnFilteredGenresNamesFromRepo() = runBlockingTest {
        val result = useCase.execute(listOf(1, 2))
        assertEquals(listOf("Action", "Crime"), result)
    }

    @Test
    fun execute_exitsOnlyOneMatchingId_returnFilteredGenresNamesFromRepo() = runBlockingTest {
        val result = useCase.execute(listOf(1, 5))
        assertEquals(listOf("Action"), result)
    }

    @Test
    fun execute_emptyList_returnEmptyList() = runBlockingTest {
        val result = useCase.execute(listOf())
        assertEquals(emptyList<Genre>(), result)
    }

    @Test
    fun execute_idsNotFoundInDB_returnEmptyList() = runBlockingTest {
        val result = useCase.execute(listOf(5, 6))
        assertEquals(emptyList<Genre>(), result)
    }

    @Test
    fun execute_genresEmptyInDB_returnEmptyList() = runBlockingTest {
        whenever(movieRepository.getGenres()).thenReturn(emptyList())
        useCase = GetGenresByIdsUseCase(movieRepository)
        val result = useCase.execute(listOf(1, 2))
        assertEquals(emptyList<Genre>(), result)
    }
}
