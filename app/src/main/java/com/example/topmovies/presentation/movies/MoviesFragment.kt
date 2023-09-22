package com.example.topmovies.presentation.movies

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.topmovies.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : ComponentActivity() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel

    @Inject lateinit var moviesViewModelFactory: MoviesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, moviesViewModelFactory)[MoviesViewModel::class.java]

        viewModel.updateMovies()

        viewModel.moviesList.observe(
                this,
        ) { movies ->
            Toast.makeText(this, "movies size is ${movies.size}", Toast.LENGTH_LONG).show()
        }
    }
}
