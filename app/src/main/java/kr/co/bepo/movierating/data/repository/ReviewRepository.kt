package kr.co.bepo.movierating.data.repository

import kr.co.bepo.movierating.domain.model.Review

interface ReviewRepository {

    suspend fun getLatestReview(movieId: String): Review?
}