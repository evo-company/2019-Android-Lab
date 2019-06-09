package com.example.evoarchexample.data

interface AuthDataRepository {
    fun logIn(email: String, password: String): String
}