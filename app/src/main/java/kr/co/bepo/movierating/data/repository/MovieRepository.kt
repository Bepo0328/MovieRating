package kr.co.bepo.movierating.data.repository

import kr.co.bepo.movierating.domain.model.Movie

interface MovieRepository {

    suspend fun getAllMovies(): List<Movie>

    suspend fun getMovies(movieIds: List<String>): List<Movie>
}