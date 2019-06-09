package com.example.evoarchexample.data

import com.example.evoarchexample.errors.WrongPasswordException

class MockAuthDataRepository : AuthDataRepository {
    override fun logIn(email: String, password: String): String {
        if (password == "111111") {
            return "token"
        }
        throw WrongPasswordException()
    }
}
