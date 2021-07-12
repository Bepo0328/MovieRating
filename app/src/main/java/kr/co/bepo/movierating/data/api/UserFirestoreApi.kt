package kr.co.bepo.movierating.data.api

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.co.bepo.movierating.DBKey
import kr.co.bepo.movierating.domain.model.User

class UserFirestoreApi(
    private val firestore: FirebaseFirestore
) : UserApi {

    override suspend fun saveUser(user: User): User =
        firestore.collection(DBKey.COLLECTION_USERS)
            .add(user)
            .await()
            .let { User(it.id) }
}