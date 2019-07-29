package evo.company.firebaselecture.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

class Notification (
    var id: String = "",
    var title: String = "",
    var body: String = "",
    @ServerTimestamp
    var date: Date? = null
)
