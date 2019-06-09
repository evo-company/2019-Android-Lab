package com.example.evoarchexample

interface AuthView {
    fun showSuccess()
    fun showAuthError()
    fun showEmailValidationError()
    fun showPasswordValidationError()
}