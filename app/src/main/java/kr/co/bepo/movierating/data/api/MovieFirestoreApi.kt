package kr.co.bepo.movierating.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import kr.co.bepo.movierating.DBKey
import kr.co.bepo.movierating.domain.model.Movie

class MovieFirestoreApi(
    private val firestore: FirebaseFirestore
) : MovieApi {

    override suspend fun getAllMovies(): List<Movie> =
        firestore.collection(DBKey.MOVIES_COLLECTION)
            .get()
            .await()
            .map { it.toObject<Movie>() }
}