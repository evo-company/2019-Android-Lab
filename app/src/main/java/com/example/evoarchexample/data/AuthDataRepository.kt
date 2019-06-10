package com.example.evoarchexample.data

import com.example.evoarchexample.errors.WrongPasswordException

interface AuthDataRepository {
    @Throws(WrongPasswordException::class)
    fun logIn(email: String, password: String): String
}
