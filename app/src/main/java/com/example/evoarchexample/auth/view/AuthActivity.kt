package com.example.evoarchexample.auth.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evoarchexample.R
import kotlinx.android.synthetic.main.activity_main.*

class AuthActivity : AppCompatActivity(), AuthView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showSuccess() {
        Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
    }

    override fun showAuthError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun showEmailValidationError() {
        email.error = getString(R.string.error)
    }

    override fun showPasswordValidationError() {
        password.error = getString(R.string.error)
    }
}
