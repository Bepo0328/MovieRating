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

    override suspend fun getAllMovieReviews(movieId: String): List<Review> =
        withContext(dispatcher) {
            reviewApi.getAllMovieReviews(movieId)
        }

    override suspend fun getAllUserReviews(userId: String): List<Review> = withContext(dispatcher) {
        reviewApi.getAllUserReviews(userId)
    }

    override suspend fun addReview(review: Review): Review = withContext(dispatcher) {
        reviewApi.addReview(review)
    }

    override suspend fun removeReview(review: Review) = withContext(dispatcher) {
        reviewApi.removeReview(review)
    }
}