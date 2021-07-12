package kr.co.bepo.movierating.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kr.co.bepo.movierating.data.api.MovieApi
import kr.co.bepo.movierating.data.api.MovieFirestoreApi
import kr.co.bepo.movierating.data.api.ReviewApi
import kr.co.bepo.movierating.data.api.ReviewFirestoreApi
import kr.co.bepo.movierating.data.repository.MovieRepository
import kr.co.bepo.movierating.data.repository.MovieRepositoryImpl
import kr.co.bepo.movierating.data.repository.ReviewRepository
import kr.co.bepo.movierating.data.repository.ReviewRepositoryImpl
import kr.co.bepo.movierating.domain.usecase.GetAllMoviesUseCase
import kr.co.bepo.movierating.domain.usecase.GetRandomFeaturedMovieUseCase
import kr.co.bepo.movierating.presentation.home.HomeContract
import kr.co.bepo.movierating.presentation.home.HomeFragment
import kr.co.bepo.movierating.presentation.home.HomePresenter
import org.koin.dsl.module

val appModule = module {
    single { Dispatchers.IO }
}
val dataModule = module {
    single { Firebase.firestore }
    single<MovieApi> { MovieFirestoreApi(get()) }
    single<ReviewApi> { ReviewFirestoreApi(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<ReviewRepository> { ReviewRepositoryImpl(get(), get()) }
}
val domainModule = module {
    factory { GetRandomFeaturedMovieUseCase(get(), get()) }
    factory { GetAllMoviesUseCase(get()) }
}

val presenterModule = module {
    scope<HomeFragment> {
        scoped<HomeContract.Presenter> { HomePresenter(getSource(), get(), get()) }
    }
}