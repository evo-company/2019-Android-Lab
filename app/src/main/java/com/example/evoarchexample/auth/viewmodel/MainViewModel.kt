package com.example.evoarchexample.auth.viewmodel

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evoarchexample.data.AuthDataRepository
import com.example.evoarchexample.errors.WrongPasswordException

class MainViewModel(private val repository: AuthDataRepository) : ViewModel() {

    companion object {
        const val MIN_PASSWORD_LENGTH = 4
    }

    val isEmailValid = MutableLiveData<Boolean>()
    val isPasswordValid = MutableLiveData<Boolean>()
    val showMessage = MutableLiveData<String>()

    /**
     * Returns authorization token if login was successful
     */
    fun logIn(email: String?, password: String?) {
        isEmailValid.value = checkEmailFormat(email)
        isPasswordValid.value = checkPasswordFormat(password)

        if (isEmailValid.value!! && isPasswordValid.value!!) {
            try {
                val token = repository.logIn(email!!, password!!)
                showMessage.value = "Login success. Token: $token"
            } catch (ex: WrongPasswordException) {
                showMessage.value = "Wrong password"
            }
        }
    }

    private fun checkEmailFormat(email: String?) = email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun checkPasswordFormat(password: String?) = password?.trim()?.length ?: 0 >= MIN_PASSWORD_LENGTH

}