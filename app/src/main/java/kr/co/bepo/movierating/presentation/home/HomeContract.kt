package kr.co.bepo.movierating.presentation.home

import kr.co.bepo.movierating.domain.model.FeaturedMovie
import kr.co.bepo.movierating.domain.model.Movie
import kr.co.bepo.movierating.presentation.BasePresenter
import kr.co.bepo.movierating.presentation.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showMovies(
            featuredMovie: FeaturedMovie?,
            movies: List<Movie>
        )
    }

    interface Presenter : BasePresenter

}