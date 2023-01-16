package com.julianswiszcz.rappi_challenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.julianswiszcz.rappi_challenge.domain.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val callBack: CallBack,
) : ListAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffCallback) {

    interface CallBack {
        fun onMovieClick(movieId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false),
            callBack
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(v: View, private val callBack: CallBack) : RecyclerView.ViewHolder(v) {
        private val img: ImageView = v.findViewById(R.id.img)
        fun bind(item: Movie) {
            item.image?.let {
                Picasso.get().load(IMAGE_URL + it).into(img)
            }
            itemView.setOnClickListener {
                callBack.onMovieClick(item.id)
            }
        }
    }

    companion object {
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}
