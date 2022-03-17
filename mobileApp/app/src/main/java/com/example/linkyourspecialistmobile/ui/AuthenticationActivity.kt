package com.example.linkyourspecialistmobile.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.linkyourspecialistmobile.R

class AuthenticationActivity : AppCompatActivity() {
    lateinit var userSharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        userSharedPreferences = getSharedPreferences("UserData", 0)
        val accessToken: String? =
            userSharedPreferences.getString("access_token", "not logged in").toString()
        if (accessToken != "not logged in") {
            val intent = Intent(this, MainNavigationActivity::class.java)
            startActivity(intent)
        }
    }

    fun startLoginActivity(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun startRegistrationActivity(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }

}
