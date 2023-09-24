package com.example.topmovies.presentation.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.topmovies.R
import com.example.topmovies.data.model.Movie
import com.example.topmovies.data.model.getImageUrl

@Composable
fun MovieDetailsScreen(movie: Movie?, genres: List<String>, onBackPressed: () -> Unit, modifier: Modifier = Modifier) {
    if (movie == null) return
    Box(modifier = modifier) {
        MovieDetailsCard(movie = movie, genres = genres, Modifier.fillMaxWidth().padding(dimensionResource(id = R.dimen.padding_medium)))
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDetailsCard(movie: Movie, genres: List<String>, modifier: Modifier = Modifier) {
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            GlideImage(
                model = movie.getImageUrl(),
                contentDescription = null,
                modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
                Text(text = movie.rating.toString(), modifier = Modifier.background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small).padding(4.dp), color = MaterialTheme.colorScheme.onPrimary)
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

            if (!movie.overview.isNullOrBlank()) {
                Text(text = movie.overview ?: "")
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            }

            LazyRow {
                items(genres) { genre ->
                    GenreCard(genre = genre, Modifier.padding(end = dimensionResource(id = R.dimen.padding_small)))
                }
            }
        }
}

@Composable
fun GenreCard(genre: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(text = genre, modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    MovieDetailsScreen(movie = Movie(1, "this is amazing movie here", "", "28/4/1993", "Talk to me dlkjdflksd jfjdslkjdslkf jds", 7.5, emptyList()), genres = listOf("Crime", "Action"), onBackPressed = { /*TODO*/ }, Modifier.fillMaxSize())
}
