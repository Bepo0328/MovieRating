package kr.co.bepo.movierating.presentation.reviews

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kr.co.bepo.movierating.domain.model.Movie
import kr.co.bepo.movierating.domain.usecase.GetAllMovieReviewsUseCase

class MovieReviewsPresenter(
    override val movie: Movie,
    private val view: MovieReviewsContract.View,
    private val getAllReviews: GetAllMovieReviewsUseCase
) : MovieReviewsContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        view.showMovieInformation(movie)
        fetchReviews()
    }

    override fun onDestroyView() {}

    private fun fetchReviews() = scope.launch {
        try {
            view.showLoadingIndicator()
            view.showReviews(getAllReviews(movie.id!!))
        } catch (e: Exception) {
            e.printStackTrace()
            view.showErrorDescription("에러가 발생했어요 😢")
        } finally {
            view.hideLoadingIndicator()
        }
    }
}