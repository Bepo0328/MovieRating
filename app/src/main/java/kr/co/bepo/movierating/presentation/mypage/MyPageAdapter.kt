package kr.co.bepo.movierating.presentation.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.bepo.movierating.databinding.ItemReviewedMovieBinding
import kr.co.bepo.movierating.domain.model.Movie
import kr.co.bepo.movierating.domain.model.ReviewedMovie
import kr.co.bepo.movierating.extension.toDecimalFormatString

class MyPageAdapter : RecyclerView.Adapter<MyPageAdapter.ViewHolder>() {

    var reviewedMovies: List<ReviewedMovie> = emptyList()
    var onMovieClickListener: ((Movie) -> Unit)? = null

    inner class ViewHolder(
        private val binding: ItemReviewedMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onMovieClickListener?.invoke(reviewedMovies[adapterPosition].movie)
            }
        }

        fun bind(item: ReviewedMovie) = with(binding) {
            Glide.with(root)
                .load(item.movie.posterUrl)
                .into(posterImageView)

            myScoreTextView.text = item.myReview.score?.toDecimalFormatString("0.0")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemReviewedMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit =
        holder.bind(reviewedMovies[position])

    override fun getItemCount(): Int = reviewedMovies.size
}