package kr.co.bepo.movierating.domain.model

data class ReviewedMovie(
    val movie: Movie,
    val myReview: Review
)