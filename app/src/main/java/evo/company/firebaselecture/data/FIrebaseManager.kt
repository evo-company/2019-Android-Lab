package evo.company.firebaselecture.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.kiwimob.firestore.coroutines.await
import evo.company.firebaselecture.model.Notification

class FirebaseManager(
    private val firestore: FirebaseFirestore
) {
    companion object {
        private const val NOTIFICATIONS = "NOTIFICATIONS"
        private const val PUSH_TOKENS = "PUSH_TOKENS"

        private fun getRandomNotificationsWithoutIds(): List<Notification> {
            return mutableListOf<Notification>().apply {
                repeat(10) {
                    add(Notification(
                        title = "title_$it",
                        body = "body_$it"
                    ))
                }
            }
        }
    }

    fun fillInData() {
        val collection = firestore.collection(NOTIFICATIONS)
        firestore.runBatch { batch ->
            getRandomNotificationsWithoutIds().forEach {
                val docId = collection.document().id
                batch.set(collection.document(docId), it.apply { it.id = docId })
            }
        }
    }

    suspend fun getAllNotifications(): List<Notification> {
        return firestore.collection(NOTIFICATIONS).await(Notification::class.java)
    }

    fun getNotificationsQuery(): Query {
        return firestore.collection(NOTIFICATIONS)
    }

    fun savePushToken(token: String) {
        firestore.collection(PUSH_TOKENS).document().set(mapOf("token" to token))
    }
}
