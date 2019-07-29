package evo.company.firebaselecture.data

import com.google.firebase.firestore.FirebaseFirestore
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


}
