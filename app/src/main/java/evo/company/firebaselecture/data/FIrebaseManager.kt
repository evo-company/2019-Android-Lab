package evo.company.firebaselecture.data

import com.google.firebase.firestore.FirebaseFirestore
import com.kiwimob.firestore.coroutines.await
import evo.company.firebaselecture.model.Notification

class FirebaseManager(
    private val firestore: FirebaseFirestore
) {
    companion object {
        private const val NOTIFICATIONS = "NOTIFICATIONS"

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
}
