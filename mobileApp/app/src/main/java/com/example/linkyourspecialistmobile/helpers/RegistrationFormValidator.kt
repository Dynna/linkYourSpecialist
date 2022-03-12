package com.example.linkyourspecialistmobile.helpers

import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText

class RegistrationFormValidator {

    private fun isEmail(editText: EditText): Boolean {
        val email: CharSequence = editText.text.toString()
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    private fun isEmpty(editText: EditText): Boolean {
        val str: CharSequence = editText.text.toString()
        return TextUtils.isEmpty(str)
    }

    private fun checkPasswordLength(editText: EditText): Boolean {
        val pass: CharSequence = editText.text.toString()
        return pass.length >= 8
    }

    fun checkDataEntered(
        email: EditText, name: EditText, surname: EditText,
        username: EditText, password: EditText
    ) {
        if (!isEmail(email)) {
            email.error = "Enter valid Email!"
        }
        if (isEmpty(name)) {
            name.error = "Name is required!"
        }
        if (isEmpty(surname)) {
            surname.error = "Surname is required!"
        }
        if (isEmpty(username)) {
            username.error = "Username is required!"
        }
        if (isEmpty(password)) {
            password.error = "Password is required!"
        }
        if (!checkPasswordLength(password)){
            password.error = "Password must have at least 8 characters!"
        }
    }
}
