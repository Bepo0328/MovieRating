package kr.co.bepo.movierating.domain.usecase

import kr.co.bepo.movierating.data.repository.ReviewRepository
import kr.co.bepo.movierating.data.repository.UserRepository
import kr.co.bepo.movierating.domain.model.MovieReviews
import kr.co.bepo.movierating.domain.model.User

class GetAllMovieReviewsUseCase(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(movieId: String): MovieReviews {
        val reviews = reviewRepository.getAllMovieReviews(movieId)
        val user = userRepository.getUser()

        if (user == null) {
            userRepository.saveUser(User())

            return MovieReviews(null, reviews)
        }

        return MovieReviews(
            reviews.find { it.userId == user.id },
            reviews.filter { it.userId != user.id }
        )
    }
}