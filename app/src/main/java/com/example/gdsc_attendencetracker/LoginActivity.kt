package com.example.gdsc_attendencetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.net.PasswordAuthentication

class LoginActivity : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var btn:Button
    lateinit var signUp:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        email = findViewById(R.id.loginEmail)
        password = findViewById(R.id.loginPassword)
        btn = findViewById(R.id.loginButton)
        signUp = findViewById(R.id.signUpTxt)

        btn.setOnClickListener {
            val emailTxt = email.text.toString()
            val passTxt = password.text.toString()

            if (emailTxt.isEmpty() || passTxt.isEmpty()) {
                Toast.makeText(this, "Please enter email or password", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(emailTxt, passTxt)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val message = it.exception?.message?:"Error Occured"
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}