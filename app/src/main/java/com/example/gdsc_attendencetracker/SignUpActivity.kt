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

class SignUpActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var passConif:EditText
    lateinit var btn:Button
    lateinit var loginTxt:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        email = findViewById(R.id.signUpEmail)
        password = findViewById(R.id.signUpPassword)
        passConif = findViewById(R.id.signUpPasswordConfirm)
        btn = findViewById(R.id.signUpButton)
        loginTxt = findViewById(R.id.loginTxt)

        loginTxt.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn.setOnClickListener {
            val emailTxt = email.text.toString()
            val passTxt = password.text.toString()
            val passConifTxt = passConif.text.toString()

            if (emailTxt.isEmpty() || passTxt.isEmpty() || passConifTxt.isEmpty()) {
                Toast.makeText(this, "Empty fields not allowed", Toast.LENGTH_SHORT).show()
            } else if (!passTxt.equals(passConifTxt)) {
                Toast.makeText(this, "passwords donot match", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(emailTxt, passTxt)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            finish()
                        } else {
                            val message = it.exception?.message?:"Error Occured"
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}