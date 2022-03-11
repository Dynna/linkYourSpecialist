package com.example.linkyourspecialistmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

    }

    fun signUp(view: View) {
        val username: EditText = findViewById(R.id.editTextUsername)
        val email: EditText = findViewById(R.id.editTextTextEmailAddress)
        val name: EditText = findViewById(R.id.editTextName)
        val surname: EditText = findViewById(R.id.editTextSurname)
        val password: EditText = findViewById(R.id.editTextPassword)
        val validator = RegistrationFormValidator()
        validator.checkDataEntered(email, name, surname, username, password)
    }
}
