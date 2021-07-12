package kr.co.bepo.movierating.domain.usecase

import kr.co.bepo.movierating.data.repository.ReviewRepository
import kr.co.bepo.movierating.data.repository.UserRepository
import kr.co.bepo.movierating.domain.model.Movie
import kr.co.bepo.movierating.domain.model.Review
import kr.co.bepo.movierating.domain.model.User

class SubmitReviewUseCase(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        movie: Movie,
        content: String,
        score: Float
    ): Review {
        var user = userRepository.getUser()

        if (user == null) {
            userRepository.saveUser(User())
            user = userRepository.getUser()
        }

        return reviewRepository.addReview(
            Review(
                userId = user!!.id,
                movieId = movie.id,
                content = content,
                score = score
            )
        )
    }
}