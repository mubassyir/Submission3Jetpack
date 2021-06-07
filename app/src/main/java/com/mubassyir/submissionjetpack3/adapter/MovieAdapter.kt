package com.mubassyir.submissionjetpack3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mubassyir.submissionjetpack3.BuildConfig
import com.mubassyir.submissionjetpack3.R
import com.mubassyir.submissionjetpack3.data.db.model.MovieEntity
import com.mubassyir.submissionjetpack3.databinding.ItemListBinding

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieHolder>(DIFF_CALLBACK) {

    companion object {
        private const val BASE_IMAGE_URL = BuildConfig.BASE_URL_IMAGE

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MovieHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            with(binding) {
                tvTitle.text = movieEntity.originalTitle
                Glide.with(root.context)
                    .load(BASE_IMAGE_URL + movieEntity.posterPath)
                    .error(R.drawable.ic_reload)
                    .override(110, 140)
                    .into(ivCover)
//                itemView.setOnClickListener {
//                    Intent(root.context, DetailMovieActivity::class.java).also {
//                        it.putExtra(EXTRA_MOVIE_ID, movieResult.id)
//                        it.putExtra(EXTRA_CLICK_ID, 0)
//                        root.context.startActivity(it)
//                    }
//                }
            }
        }
    }
}
