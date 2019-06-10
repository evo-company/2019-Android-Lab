package com.example.evoarchexample.data

import com.example.evoarchexample.errors.WrongPasswordException

class MockAuthDataRepository : AuthDataRepository {

    companion object {
        private const val CORRECT_PASSWORD = "111111"
        private const val CORRECT_RESPONSE = "a0dwKMD94"
    }

    @Throws(WrongPasswordException::class)
    override fun logIn(email: String, password: String): String {
        if (password == CORRECT_PASSWORD) {
            return CORRECT_RESPONSE
        } else {
            throw WrongPasswordException()
        }
    }
}
