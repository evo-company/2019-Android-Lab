package evo.company.firebaselecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.kiwimob.firestore.coroutines.await
import evo.company.firebaselecture.data.FirebaseManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val firebaseManager = FirebaseManager(FirebaseFirestore.getInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillInData()
        getNotificaitons()
    }

    private fun fillInData() {
        firebaseManager.fillInData()
    }

    private fun getNotificaitons() {
        GlobalScope.launch {
            val notifications = firebaseManager.getAllNotifications()
            withContext(Dispatchers.Main) {
                notificationRv.adapter = NotificationAdapter(notifications)
            }
        }
    }
}
