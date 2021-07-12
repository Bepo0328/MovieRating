package kr.co.bepo.movierating.data.api

import kr.co.bepo.movierating.domain.model.User

interface UserApi {

    suspend fun saveUser(user: User): User
}