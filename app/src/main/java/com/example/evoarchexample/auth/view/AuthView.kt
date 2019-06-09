package com.example.evoarchexample.auth.view

interface AuthView {
    fun showSuccess()
    fun showAuthError()
    fun showEmailValidationError()
    fun showPasswordValidationError()
    fun showError(errorMessage: String)
}