package evo.company.firebaselecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.kiwimob.firestore.coroutines.await
import evo.company.firebaselecture.data.FirebaseManager

class MainActivity : AppCompatActivity() {

    private val firebaseManager = FirebaseManager(FirebaseFirestore.getInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillInData()
    }

    private fun fillInData() {
        firebaseManager.fillInData()
    }
}
