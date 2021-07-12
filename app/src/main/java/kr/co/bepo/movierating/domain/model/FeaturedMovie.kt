package kr.co.bepo.movierating.domain.model

data class FeaturedMovie(
    val movie: Movie,
    val latestReview: Review?
)