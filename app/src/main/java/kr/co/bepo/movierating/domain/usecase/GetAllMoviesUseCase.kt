package kr.co.bepo.movierating.domain.usecase

import kr.co.bepo.movierating.data.repository.MovieRepository
import kr.co.bepo.movierating.domain.model.Movie

class GetAllMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): List<Movie> = movieRepository.getAllMovies()
}