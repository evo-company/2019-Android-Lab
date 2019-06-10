package com.example.evoarchexample.auth.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.evoarchexample.R
import com.example.evoarchexample.data.MockAuthDataRepository
import com.example.evoarchexample.databinding.ActivityMainBinding
import com.example.evoarchexample.auth.viewmodel.MainViewModel
import com.example.evoarchexample.auth.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, MainViewModelFactory(MockAuthDataRepository())).get(MainViewModel::class.java)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        viewModel.apply {
            val activity = this@MainActivity

            showMessage.observe(activity, Observer {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            })

            isEmailValid.observe(activity, Observer { valid ->
                emailTextInputLayout.error = if (!valid) getString(R.string.error_wrong_email) else null
            })

            isPasswordValid.observe(activity, Observer {valid ->
                passwordTextInputLayout.error = if (!valid) {
                    String.format(getString(R.string.format_error_short_password), MainViewModel.MIN_PASSWORD_LENGTH)
                } else {
                    null
                }
            })
        }
    }
}
