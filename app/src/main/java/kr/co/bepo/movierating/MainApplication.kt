package kr.co.bepo.movierating

import android.app.Application
import androidx.viewbinding.BuildConfig
import kr.co.bepo.movierating.di.appModule
import kr.co.bepo.movierating.utillity.MovieDataGenerator
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) Level.DEBUG
                else Level.NONE
            )
            androidContext(this@MainApplication)
            modules(appModule)
        }
        // FireStore 에 Movie 더미 데이터 생성용
//        MovieDataGenerator().generate()
    }
}