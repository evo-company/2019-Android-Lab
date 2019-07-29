package evo.company.firebaselecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.kiwimob.firestore.coroutines.await
import evo.company.firebaselecture.data.FirebaseManager
import evo.company.firebaselecture.data.PaginationDelegate
import evo.company.firebaselecture.model.Notification
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val firebaseManager = FirebaseManager(FirebaseFirestore.getInstance())
    private val paginationDelegate = PaginationDelegate(
        10,
        Notification::class.java,
        firebaseManager.getNotificationsQuery()
    )
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillInData()
        adapter = NotificationAdapter(mutableListOf()) {
            pagination()
        }
        notificationRv.adapter = adapter
        pagination()
        //getNotificaitons()
    }

    private fun fillInData() {
        firebaseManager.fillInData()
    }

//    private fun getNotificaitons() {
//        GlobalScope.launch {
//            val notifications = firebaseManager.getAllNotifications()
//            withContext(Dispatchers.Main) {
//                notificationRv.adapter = NotificationAdapter(notifications)
//            }
//        }
//    }

    private fun pagination() {
        GlobalScope.launch {
            val notifications = paginationDelegate.getNextPage()
            withContext(Dispatchers.Main) {
                adapter.appendList(notifications)
            }
        }
    }
}
