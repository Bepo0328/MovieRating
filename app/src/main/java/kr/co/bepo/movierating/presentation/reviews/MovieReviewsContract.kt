package kr.co.bepo.movierating.presentation.reviews

import kr.co.bepo.movierating.domain.model.Movie
import kr.co.bepo.movierating.domain.model.MovieReviews
import kr.co.bepo.movierating.domain.model.Review
import kr.co.bepo.movierating.presentation.BasePresenter
import kr.co.bepo.movierating.presentation.BaseView

interface MovieReviewsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showMovieInformation(movie: Movie)

        fun showReviews(reviews: MovieReviews)

        fun showErrorToast(message: String)
    }

    interface Presenter : BasePresenter {

        val movie: Movie

        fun requestAddReview(content: String, score: Float)

        fun requestRemoveReview(review: Review)
    }
}