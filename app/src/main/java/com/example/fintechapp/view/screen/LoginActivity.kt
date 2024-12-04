package com.example.fintechapp.view.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fintechapp.R
import com.example.fintechapp.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private  lateinit var auth: FirebaseAuth
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        val emailField = findViewById<EditText>(R.id.emailEditText)
        val passwordField = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val loadingProgressBar = findViewById<ProgressBar>(R.id.loadingProgressBar)

        loginButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loadingProgressBar.visibility = ProgressBar.VISIBLE
                viewModel.login(email, password)
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginStatus.observe(this) { isLoggedIn ->
            loadingProgressBar.visibility = ProgressBar.GONE
            if (isLoggedIn) {
                startActivity(Intent(this, AccountActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
