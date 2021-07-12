package kr.co.bepo.movierating.data.repository

import kr.co.bepo.movierating.domain.model.User

interface UserRepository {

    suspend fun getUser(): User?

    suspend fun saveUser(user: User)
}