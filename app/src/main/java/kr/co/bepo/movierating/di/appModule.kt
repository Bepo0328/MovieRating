package kr.co.bepo.movierating.di

import android.app.Activity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kr.co.bepo.movierating.data.api.*
import kr.co.bepo.movierating.data.preference.PreferenceManager
import kr.co.bepo.movierating.data.preference.SharedPreferenceManager
import kr.co.bepo.movierating.data.repository.*
import kr.co.bepo.movierating.domain.model.Movie
import kr.co.bepo.movierating.domain.usecase.*
import kr.co.bepo.movierating.presentation.home.HomeContract
import kr.co.bepo.movierating.presentation.home.HomeFragment
import kr.co.bepo.movierating.presentation.home.HomePresenter
import kr.co.bepo.movierating.presentation.mypage.MyPageContract
import kr.co.bepo.movierating.presentation.mypage.MyPageFragment
import kr.co.bepo.movierating.presentation.mypage.MyPagePresenter
import kr.co.bepo.movierating.presentation.reviews.MovieReviewsContract
import kr.co.bepo.movierating.presentation.reviews.MovieReviewsFragment
import kr.co.bepo.movierating.presentation.reviews.MovieReviewsPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { Dispatchers.IO }
}
val dataModule = module {
    single { Firebase.firestore }
    single<MovieApi> { MovieFirestoreApi(get()) }
    single<ReviewApi> { ReviewFirestoreApi(get()) }
    single<UserApi> { UserFirestoreApi(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<ReviewRepository> { ReviewRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }

    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }
}
val domainModule = module {
    factory { GetRandomFeaturedMovieUseCase(get(), get()) }
    factory { GetAllMoviesUseCase(get()) }
    factory { GetAllMovieReviewsUseCase(get(), get()) }
    factory { GetMyReviewedMoviesUseCase(get(), get(), get()) }
    factory { SubmitReviewUseCase(get(), get()) }
    factory { DeleteReviewUseCase(get()) }
}

val presenterModule = module {
    scope<HomeFragment> {
        scoped<HomeContract.Presenter> { HomePresenter(getSource(), get(), get()) }
    }
    scope<MovieReviewsFragment> {
        scoped<MovieReviewsContract.Presenter> { (movie: Movie) ->
            MovieReviewsPresenter(movie, getSource(), get(), get(), get())
        }
    }
    scope<MyPageFragment> {
        scoped<MyPageContract.Presenter> { MyPagePresenter(getSource(), get()) }
    }
}