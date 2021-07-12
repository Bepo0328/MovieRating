package kr.co.bepo.movierating.data.api

import kr.co.bepo.movierating.domain.model.Movie

interface MovieApi {

    suspend fun getAllMovies(): List<Movie>

    suspend fun getMovies(movieIds: List<String>): List<Movie>
}