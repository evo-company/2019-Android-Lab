package com.example.evoarchexample.data

import androidx.lifecycle.LiveData

interface AuthDataRepository {
    fun logIn(email: String, password: String): LiveData<String>
}
