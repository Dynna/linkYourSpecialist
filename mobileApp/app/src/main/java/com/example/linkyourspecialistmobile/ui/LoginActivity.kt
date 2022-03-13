package com.example.linkyourspecialistmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.data.LoginRequestModel
import com.example.linkyourspecialistmobile.viewmodel.LoginViewModel
import com.example.linkyourspecialistmobile.viewmodel.RegistrationViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        viewModel = LoginViewModel()
    }

    fun loginUser(view: View) {
        val email: EditText = findViewById(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById(R.id.editTextPassword)
        val loginRequestModel = LoginRequestModel()
        loginRequestModel.email = email.text.toString()
        loginRequestModel.password = password.text.toString()

        viewModel.login(loginRequestModel)
        viewModel.loginLiveData?.observe(
            this, Observer {
                if (it != null) {
                    showToast("User logged in successfully")
                } else {
                    showToast("Cannot login user at moment")
                }
            }
        )
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
