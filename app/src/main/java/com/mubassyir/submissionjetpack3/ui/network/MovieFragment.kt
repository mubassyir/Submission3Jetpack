package com.mubassyir.submissionjetpack3.ui.network

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mubassyir.submissionjetpack3.R
import com.mubassyir.submissionjetpack3.adapter.MovieAdapter
import com.mubassyir.submissionjetpack3.databinding.FragmentMovieBinding
import com.mubassyir.submissionjetpack3.viewModel.MovieViewModel
import com.mubassyir.submissionjetpack3.viewModel.ViewModelFactory
import com.mubassyir.submissionjetpack3.vo.Status

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var fragmentBlankBinding: FragmentMovieBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieBinding.bind(view)
        fragmentBlankBinding = binding

        val factory =  ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]
        val movieAdapter = MovieAdapter()

        viewModel.getAllMovie().observe(requireActivity(), { movie->
            movie?.let {
                when(movie.status){
                    Status.LOADING -> {
                        binding.pbMovie.visibility = View.VISIBLE
                    }

                    Status.SUCCESS ->{
                        binding.pbMovie.visibility = View.INVISIBLE
                        movieAdapter.submitList(movie.data)
                        Log.d("Data Received ", movie.data.toString())
                    }

                    Status.ERROR -> {
                        binding.pbMovie.visibility = View.INVISIBLE
                        Toast.makeText(context, "Something error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvMovie){
            this.adapter = movieAdapter
            this.layoutManager = GridLayoutManager(context,3)
            this.setHasFixedSize(true)
        }
    }
}