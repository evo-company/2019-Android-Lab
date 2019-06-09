package com.example.evoarchexample.auth.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evoarchexample.R
import com.example.evoarchexample.auth.presentation.AuthPresenter
import com.example.evoarchexample.data.MockAuthDataRepository
import kotlinx.android.synthetic.main.activity_main.*

class AuthActivity : AppCompatActivity(), AuthView {

    private val presenter: AuthPresenter by lazy {
        AuthPresenter(this, MockAuthDataRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login.setOnClickListener {
            presenter.login(email.text.toString(), password.text.toString())
        }
    }

    override fun showSuccess() {
        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
    }

    override fun showAuthError() = showError(getString(R.string.error))

    override fun showEmailValidationError() {
        email.error = getString(R.string.error)
    }

    override fun showPasswordValidationError() {
        password.error = getString(R.string.error)
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }
}
