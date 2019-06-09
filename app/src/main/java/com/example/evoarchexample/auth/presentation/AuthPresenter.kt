package com.example.evoarchexample.auth.presentation

import android.util.Patterns
import com.example.evoarchexample.auth.view.AuthView
import com.example.evoarchexample.data.AuthDataRepository
import com.example.evoarchexample.errors.WrongPasswordException

class AuthPresenter(private val view: AuthView, private val authDataRepository: AuthDataRepository) {

    fun login(email: String, password: String) {
        var noErrors = true

        if (!email.isValidEmail()) {
            noErrors = false
            view.showEmailValidationError()
        }
        if (!password.isValidPassword()) {
            noErrors = false
            view.showPasswordValidationError()
        }

        if (noErrors) {
            try {
                saveToken(authDataRepository.logIn(email, password))
                view.showSuccess()
            } catch (e: WrongPasswordException) {
                view.showAuthError()
            } catch (e: Exception) {
                e.message?.let { view.showError(it) }
            }
        }
    }

    private fun saveToken(token: String) {
    }

    private fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidPassword(): Boolean = length > 5

}