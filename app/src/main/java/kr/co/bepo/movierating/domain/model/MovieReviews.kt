package kr.co.bepo.movierating.domain.model

data class MovieReviews(
    val myReview: Review?,
    val otherReview: List<Review>
)