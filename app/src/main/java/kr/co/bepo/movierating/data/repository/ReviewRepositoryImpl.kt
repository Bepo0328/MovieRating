package kr.co.bepo.movierating.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kr.co.bepo.movierating.data.api.ReviewApi
import kr.co.bepo.movierating.domain.model.Review

class ReviewRepositoryImpl(
    private val reviewApi: ReviewApi,
    private val dispatcher: CoroutineDispatcher
) : ReviewRepository {

    override suspend fun getLatestReview(movieId: String): Review? = withContext(dispatcher) {
        reviewApi.getLatestReview(movieId)
    }
}