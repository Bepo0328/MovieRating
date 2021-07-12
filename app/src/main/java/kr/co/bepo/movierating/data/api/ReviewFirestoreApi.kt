package kr.co.bepo.movierating.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import kr.co.bepo.movierating.DBKey
import kr.co.bepo.movierating.domain.model.Movie
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

    override suspend fun getAllMovieReviews(movieId: String): List<Review> =
        firestore.collection(DBKey.COLLECTION_REVIEWS)
            .whereEqualTo(DBKey.FIELD_MOVIE_ID, movieId)
            .orderBy(DBKey.FIELD_CREATED_AT, Query.Direction.DESCENDING)
            .get()
            .await()
            .map { it.toObject<Review>() }

    override suspend fun getAllUserReviews(userId: String): List<Review> =
        firestore.collection(DBKey.COLLECTION_REVIEWS)
            .whereEqualTo(DBKey.FIELD_USER_ID, userId)
            .orderBy(DBKey.FIELD_CREATED_AT, Query.Direction.DESCENDING)
            .get()
            .await()
            .map { it.toObject<Review>() }


    override suspend fun addReview(review: Review): Review {
        val newReviewReference = firestore.collection(DBKey.COLLECTION_REVIEWS).document()
        val movieReference =
            firestore.collection(DBKey.COLLECTION_MOVIES).document(review.movieId!!)

        firestore.runTransaction { transaction ->
            val movie = transaction.get(movieReference).toObject<Movie>()!!

            val oldAverageScore = movie.averageScore ?: 0f
            val oldNumberOfScore = movie.numberOfScore ?: 0
            val oldTotalScore = oldAverageScore * oldNumberOfScore

            val newNumberOfScore = oldNumberOfScore + 1
            val newAverageScore = (oldTotalScore + (review.score ?: 0f)) / newNumberOfScore

            transaction.set(
                movieReference,
                movie.copy(
                    numberOfScore = newNumberOfScore,
                    averageScore = newAverageScore
                )
            )

            transaction.set(
                newReviewReference,
                review,
                SetOptions.merge()
            )
        }.await()

        return newReviewReference.get().await().toObject<Review>()!!
    }

    override suspend fun removeReview(review: Review) {
        val reviewReference = firestore.collection(DBKey.COLLECTION_REVIEWS).document(review.id!!)
        val movieReference =
            firestore.collection(DBKey.COLLECTION_MOVIES).document(review.movieId!!)

        firestore.runTransaction { transaction ->
            val movie = transaction
                .get(movieReference)
                .toObject<Movie>()!!

            val oldAverageScore = movie.averageScore ?: 0f
            val oldNumberOfScore = movie.numberOfScore ?: 0
            val oldTotalScore = oldAverageScore * oldNumberOfScore

            val newNumberOfScore = (oldNumberOfScore - 1).coerceAtLeast(0)
            val newAverageScore =
                if (newNumberOfScore > 0) (oldTotalScore - (review.score ?: 0f)) / newNumberOfScore
                else 0f

            transaction.set(
                movieReference,
                movie.copy(
                    numberOfScore = newNumberOfScore,
                    averageScore = newAverageScore
                )
            )

            transaction.delete(reviewReference)
        }.await()
    }
}