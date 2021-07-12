package kr.co.bepo.movierating.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import kr.co.bepo.movierating.DBKey
import kr.co.bepo.movierating.domain.model.Review

class ReviewFirestoreApi(
    private val firestore: FirebaseFirestore
) : ReviewApi {

    override suspend fun getLatestReview(movieId: String): Review? =
        firestore.collection(DBKey.REVIEWS_COLLECTION)
            .whereEqualTo("movieId", movieId)
            .orderBy("createAt", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()
            .map { it.toObject<Review>() }
            .firstOrNull()
}