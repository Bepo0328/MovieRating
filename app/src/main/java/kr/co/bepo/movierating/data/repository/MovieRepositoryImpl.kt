package kr.co.bepo.movierating.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kr.co.bepo.movierating.data.api.MovieApi
import kr.co.bepo.movierating.domain.model.Movie

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val dispatcher: CoroutineDispatcher
): MovieRepository {

    override suspend fun getAllMovies(): List<Movie> = withContext(dispatcher) {
        movieApi.getAllMovies()
    }
}