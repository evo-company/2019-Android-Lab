package com.example.evoarchexample.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.evoarchexample.R
import com.example.evoarchexample.data.MockAuthDataRepository
import com.example.evoarchexample.databinding.ActivityMainBinding
import com.example.evoarchexample.databinding.ActivityMainBindingImpl
import com.example.evoarchexample.viewmodel.MainViewModel
import com.example.evoarchexample.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, MainViewModelFactory(MockAuthDataRepository())).get(MainViewModel::class.java)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        configureView()

        viewModel.showMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun configureView() {
        emailTextInputLayout.error = "Wrong email format"
        passwordTextInputLayout.error = "Must have at least ${MainViewModel.MIN_PASSWORD_LENGTH} symbols"
    }
}
