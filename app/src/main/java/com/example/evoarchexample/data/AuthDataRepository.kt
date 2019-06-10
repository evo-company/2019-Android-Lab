package com.example.evoarchexample.data

import com.example.evoarchexample.errors.WrongPasswordException

interface AuthDataRepository {

    /**
     * Returns authorization token if login was successful
     */
    @Throws(WrongPasswordException::class)
    fun logIn(email: String, password: String): String
}
