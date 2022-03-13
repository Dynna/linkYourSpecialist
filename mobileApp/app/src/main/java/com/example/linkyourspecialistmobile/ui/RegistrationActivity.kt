package com.example.linkyourspecialistmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.data.RegistrationRequestModel
import com.example.linkyourspecialistmobile.helpers.RegistrationFormValidator
import com.example.linkyourspecialistmobile.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        viewModel = RegistrationViewModel()
    }

    fun signUp(view: View) {
        val username: EditText = findViewById(R.id.editTextUsername)
        val email: EditText = findViewById(R.id.editTextTextEmailAddress)
        val name: EditText = findViewById(R.id.editTextName)
        val surname: EditText = findViewById(R.id.editTextSurname)
        val password: EditText = findViewById(R.id.editTextPassword)
        val validator = RegistrationFormValidator()
        validator.checkDataEntered(email, name, surname, username, password)

        Log.d("USERNAME", username.text.toString())

        val registrationRequestModel = RegistrationRequestModel()
        registrationRequestModel.username = username.text.toString()
        registrationRequestModel.email = email.text.toString()
        registrationRequestModel.name = name.text.toString()
        registrationRequestModel.surname = surname.text.toString()
        registrationRequestModel.password = password.text.toString()

        viewModel.signup(registrationRequestModel)
        viewModel.registrationLiveData?.observe(this, Observer {
            if (it != null) {
                showToast("New user registered successfully")
            } else {
                showToast("Cannot register new user at moment")
            }
        })

    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
