package com.example.topmovies.presentation.movies

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.topmovies.MovieCallState
import com.example.topmovies.R
import com.example.topmovies.data.model.Movie

@Composable
fun MoviesScreen(
        movies: List<Movie>,
        movieCallStatus: MovieCallState,
        onMovieClicked: (Movie) -> Unit,
        modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    if (movieCallStatus == MovieCallState.ERROR) {
        Toast.makeText(context, stringResource(id = R.string.fetching_movies_error), Toast.LENGTH_SHORT).show()
    }
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            MovieCard(movie = movie, onMovieClicked = { onMovieClicked(it) })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(
        movie: Movie,
        onMovieClicked: (Movie) -> Unit,
        modifier: Modifier = Modifier,
) {
    Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = modifier
                    .clickable { onMovieClicked(movie) }
                    .padding(dimensionResource(id = R.dimen.padding_small)),
    ) {
        Column {
            GlideImage(
                    model = "https://image.tmdb.org/t/p/w500" + movie.posterPath,
                    contentDescription = null,
                    modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                    contentScale = ContentScale.Crop,
            )
            MovieInfo(movie = movie, Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}

@Composable
fun MovieInfo(
        movie: Movie,
        modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(text = movie.title, style = MaterialTheme.typography.titleSmall)
        Text(text = movie.releaseDate ?: "")
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MoviesScreen(movies = listOf(Movie(1, "test", "", "30/5/2001", "Talk to me", 7.2)),MovieCallState.SUCCESS, onMovieClicked = {})
}
