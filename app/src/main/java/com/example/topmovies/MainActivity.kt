package com.example.topmovies

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.topmovies.databinding.ActivityMainBinding
import com.example.topmovies.presentation.movies.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            startActivity(Intent(this, MoviesFragment::class.java))
        }

    }
}
