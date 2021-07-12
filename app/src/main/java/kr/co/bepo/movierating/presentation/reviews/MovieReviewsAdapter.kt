package kr.co.bepo.movierating.presentation.reviews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import kr.co.bepo.movierating.databinding.ItemMovieInformationBinding
import kr.co.bepo.movierating.databinding.ItemMyReviewBinding
import kr.co.bepo.movierating.databinding.ItemReviewBinding
import kr.co.bepo.movierating.databinding.ItemReviewFormBinding
import kr.co.bepo.movierating.domain.model.Movie
import kr.co.bepo.movierating.domain.model.Review
import kr.co.bepo.movierating.extension.toAbbreviatedString
import kr.co.bepo.movierating.extension.toDecimalFormatString

class MovieReviewsAdapter(private val movie: Movie) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_VIEW_TYPE_HEADER = 0
        const val ITEM_VIEW_TYPE_ITEM = 1
        const val ITEM_VIEW_TYPE_REVIEW_FORM = 2
        const val ITEM_VIEW_TYPE_MY_REVIEW = 3
    }

    var myReview: Review? = null
    var reviews: List<Review> = emptyList()

    var onReviewSubmitButtonClickListener: ((content: String, score: Float) -> Unit)? = null
    var onReviewDeleteButtonClickListener: ((Review) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> {
                MovieInformationViewHolder(
                    ItemMovieInformationBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ITEM_VIEW_TYPE_ITEM -> {
                ReviewViewHolder(parent)
            }
            ITEM_VIEW_TYPE_REVIEW_FORM -> {
                ReviewFormViewHolder(
                    ItemReviewFormBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ITEM_VIEW_TYPE_MY_REVIEW -> {
                MyReviewViewHolder(
                    ItemMyReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> throw RuntimeException("알 수 없는 ViewType 입니다.")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieInformationViewHolder -> {
                holder.bind(movie)
            }
            is ReviewViewHolder -> {
                holder.bind(reviews[position - 2])
            }
            is MyReviewViewHolder -> {
                myReview ?: return
                holder.bind(myReview!!)
            }
            is ReviewFormViewHolder -> Unit
            else -> throw RuntimeException("알 수 없는 ViewHolder 입니다.")
        }
    }


    override fun getItemCount(): Int = 2 + reviews.size

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> ITEM_VIEW_TYPE_HEADER
            1 -> {
                if (myReview == null) ITEM_VIEW_TYPE_REVIEW_FORM
                else ITEM_VIEW_TYPE_MY_REVIEW
            }
            else -> ITEM_VIEW_TYPE_ITEM
        }

    inner class MovieInformationViewHolder(private val binding: ItemMovieInformationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Movie) {
            Glide.with(binding.root)
                .load(item.posterUrl)
                .into(binding.posterImageView)

            item.let {
                binding.averageScoreTextView.text =
                    "평점 ${it.averageScore?.toDecimalFormatString("0.0")} (${it.numberOfScore?.toAbbreviatedString()})"
                binding.titleTextView.text = it.title
                binding.additionalInformationTextView.text = "${it.releaseYear}·${it.country}"
                binding.relationsTextView.text = "감독: ${it.director}\n출연진: ${it.actors}"
                binding.genreChipGroup.removeAllViews()
                it.genre?.split(" ")?.forEach { genre ->
                    binding.genreChipGroup.addView(
                        Chip(binding.root.context).apply {
                            isClickable = false
                            text = genre
                        }
                    )
                }
            }
        }
    }

    inner class ReviewViewHolder(
        parent: ViewGroup,
        private val binding: ItemReviewBinding = ItemReviewBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Review) = with(binding) {
            item.let {
                authorIdTextView.text = "${it.userId?.take(3)}***"
                scoreTextView.text = it.score?.toDecimalFormatString("0.0")
                contentsTextView.text = "\"${it.content}\""
            }
        }
    }

    @SuppressLint("SetTextI18n")
    inner class ReviewFormViewHolder(
        private val binding: ItemReviewFormBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.submitButton.setOnClickListener {
                onReviewSubmitButtonClickListener?.invoke(
                    binding.reviewFieldEditText.text.toString(),
                    binding.ratingBar.rating
                )
            }

            binding.reviewFieldEditText.addTextChangedListener { editable ->
                binding.contentLimitTextView.text = "(${editable?.length ?: 0}/500)"
                binding.submitButton.isEnabled = (editable?.length ?: 0) >= 5
            }
        }
    }

    inner class MyReviewViewHolder(
        private val binding: ItemMyReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.deleteButton.setOnClickListener {
                onReviewDeleteButtonClickListener?.invoke(myReview!!)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Review) = with(binding) {
            item.let {
                scoreTextView.text = it.score?.toDecimalFormatString("0.0")
                contentsTextView.text = "\"${it.content}\""
            }
        }
    }


}