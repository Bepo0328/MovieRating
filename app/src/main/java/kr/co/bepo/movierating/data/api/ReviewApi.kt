package kr.co.bepo.movierating.data.api

import kr.co.bepo.movierating.domain.model.Review

interface ReviewApi {

    suspend fun getLatestReview(movieId: String): Review?

    suspend fun getAllReviews(movieId: String): List<Review>
}