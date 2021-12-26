package com.androidxlab.dependencyinjection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.androidxlab.dependencyinjection.databinding.ActivityMainBinding
import com.androidxlab.dependencyinjection.movieslist.MovieViewModel
import com.androidxlab.dependencyinjection.movieslist.ui.ImageSliderAdapter
import com.androidxlab.dependencyinjection.movieslist.ui.MovieListAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding
    private var adapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).daggerComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        model.fetchPopularMovies()
        model.fetchLatestMovies()

        binding.rvMovie.layoutManager = GridLayoutManager(this, 3)
       // binding.rvMovie.setHasFixedSize(true)
        binding.rvMovie.adapter  = adapter

        model.movieData.observe(this, {
            adapter.submitData(lifecycle,it)

        })

        model.movieLatestData.observe(this, {
            it?.let {
                val imageSliderAdapter = ImageSliderAdapter(it.result)
                binding.viewPagerMain.adapter = imageSliderAdapter
            }
        })
    }

}