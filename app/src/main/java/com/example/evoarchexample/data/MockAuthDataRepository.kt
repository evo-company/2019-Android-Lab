package com.example.evoarchexample.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.evoarchexample.errors.WrongPasswordException

class MockAuthDataRepository : AuthDataRepository {

    companion object {
        private const val CORRECT_PASSWORD = "111111"
        private const val CORRECT_RESPONSE = "token"
    }

    @Throws(WrongPasswordException::class)
    override fun logIn(email: String, password: String): LiveData<String> {
        val responseData = MutableLiveData<String>()
        if (password == CORRECT_PASSWORD) {
            responseData.value = CORRECT_RESPONSE
        } else {
            throw WrongPasswordException()
        }

        return responseData
    }
}
