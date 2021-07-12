package kr.co.bepo.movierating.domain.usecase

import kr.co.bepo.movierating.data.repository.ReviewRepository
import kr.co.bepo.movierating.domain.model.Review

class GetAllReviewsUseCase(private val reviewRepository: ReviewRepository) {

    suspend operator fun invoke(movieId: String): List<Review> =
        reviewRepository.getAllReviews(movieId)
}