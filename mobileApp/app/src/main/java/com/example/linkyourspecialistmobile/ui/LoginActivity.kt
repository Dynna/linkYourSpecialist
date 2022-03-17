package com.example.linkyourspecialistmobile.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.linkyourspecialistmobile.R
import com.example.linkyourspecialistmobile.data.LoginRequestModel
import com.example.linkyourspecialistmobile.viewmodel.LoginViewModel
import com.example.linkyourspecialistmobile.viewmodel.RegistrationViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var userSharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        viewModel = LoginViewModel()

        userSharedPreferences = getSharedPreferences("UserData", 0)
        sharedPreferencesEditor = userSharedPreferences.edit()
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
                    sharedPreferencesEditor.putString(
                        "access_token",
                        viewModel.loginLiveData!!.value?.access_token.toString()
                    )
                    sharedPreferencesEditor.putString(
                        "user_data",
                        viewModel.loginLiveData!!.value?.user.toString()
                    )
                    sharedPreferencesEditor.apply()
                    Log.d(
                        "TOKEN",
                        userSharedPreferences.getString("access_token", "not logged in").toString()
                    )
                    val intent = Intent(this, MainNavigationActivity::class.java)
                    startActivity(intent)
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
