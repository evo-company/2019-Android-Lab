package com.example.evoarchexample.viewmodel

import android.util.Patterns
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evoarchexample.data.AuthDataRepository
import com.example.evoarchexample.errors.WrongPasswordException

class MainViewModel(private val repository: AuthDataRepository) : ViewModel() {

    companion object {
        const val MIN_PASSWORD_LENGTH = 4
    }

    val isEmailValid = ObservableBoolean(true)
    val isPasswordValid = ObservableBoolean(true)
    val showMessage = MutableLiveData<String>()

    /**
     * Returns authorization token if login was successful
     */
    fun logIn(email: String?, password: String?) {
        isEmailValid.set(isEmailValid(email))
        isPasswordValid.set(isPasswordValid(password))

        if (isEmailValid.get() && isPasswordValid.get()) {
            try {
                val token = repository.logIn(email!!, password!!)
                showMessage.value = "Login success. Token: $token"
            } catch (ex: WrongPasswordException) {
                showMessage.value = "Wrong password"
            }
        }
    }

    private fun isEmailValid(email: String?) = email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordValid(password: String?) = password?.trim()?.length ?: 0 >= MIN_PASSWORD_LENGTH

}