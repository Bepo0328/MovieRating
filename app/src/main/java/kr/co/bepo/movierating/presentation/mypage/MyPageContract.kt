package kr.co.bepo.movierating.presentation.mypage

import kr.co.bepo.movierating.domain.model.ReviewedMovie
import kr.co.bepo.movierating.presentation.BasePresenter
import kr.co.bepo.movierating.presentation.BaseView

interface MyPageContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showNoDataDescription(message: String)

        fun showErrorDescription(message: String)

        fun showReviewedMovies(reviewedMovies: List<ReviewedMovie>)
    }

    interface Presenter : BasePresenter
}