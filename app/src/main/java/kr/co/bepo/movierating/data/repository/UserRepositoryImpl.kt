package kr.co.bepo.movierating.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kr.co.bepo.movierating.data.api.UserApi
import kr.co.bepo.movierating.data.preference.PreferenceManager
import kr.co.bepo.movierating.domain.model.User

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    companion object {
        private const val KEY_USER_ID = "KEY_USER_ID"
    }

    override suspend fun getUser(): User? = withContext(dispatcher) {
        preferenceManager.getString(KEY_USER_ID)?.let { User(it) }
    }

    override suspend fun saveUser(user: User) = withContext(dispatcher) {
        val newUser = userApi.saveUser(user)
        preferenceManager.putString(KEY_USER_ID, newUser.id!!)
    }
}