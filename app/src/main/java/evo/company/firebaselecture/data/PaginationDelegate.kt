package evo.company.firebaselecture.data

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.kiwimob.firestore.coroutines.await

class PaginationDelegate<T>(
    private val pageSize: Int,
    private val itemClass: Class<T>,
    private val query: Query
) {
    private var lastDocument: DocumentSnapshot? = null

    suspend fun getNextPage(): List<T> {
        var actualQuery = query.limit(pageSize.toLong())
        lastDocument?.let {
            actualQuery = actualQuery.startAfter(it)
        }

        val querySnapshot = actualQuery.await()
        if (querySnapshot.isEmpty) return emptyList()
        val res = querySnapshot.map { it.toObject(itemClass) }
        lastDocument = querySnapshot.documents.last()
        return res
    }
}
