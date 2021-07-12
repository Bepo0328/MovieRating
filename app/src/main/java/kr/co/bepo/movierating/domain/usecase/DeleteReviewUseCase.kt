package kr.co.bepo.movierating.domain.usecase

import kr.co.bepo.movierating.data.repository.ReviewRepository
import kr.co.bepo.movierating.domain.model.Review

class DeleteReviewUseCase(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(review: Review) =
        reviewRepository.removeReview(review)
}