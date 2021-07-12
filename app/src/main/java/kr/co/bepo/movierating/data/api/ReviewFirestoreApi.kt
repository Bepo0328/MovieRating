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
        firestore.collection(DBKey.COLLECTION_REVIEWS)
            .whereEqualTo(DBKey.FIELD_MOVIE_ID, movieId)
            .orderBy(DBKey.FIELD_CREATED_AT, Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()
            .map { it.toObject<Review>() }
            .firstOrNull()

    override suspend fun getAllReviews(movieId: String): List<Review> =
        firestore.collection(DBKey.COLLECTION_REVIEWS)
            .whereEqualTo(DBKey.FIELD_MOVIE_ID, movieId)
            .orderBy(DBKey.FIELD_CREATED_AT, Query.Direction.DESCENDING)
            .get()
            .await()
            .map { it.toObject<Review>() }
}