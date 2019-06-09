package com.example.evoarchexample.data

interface AuthDataRepository {
    fun logIn(username: String, password: String): String
}