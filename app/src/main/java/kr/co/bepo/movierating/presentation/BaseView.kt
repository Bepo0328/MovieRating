package kr.co.bepo.movierating.presentation

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT
}