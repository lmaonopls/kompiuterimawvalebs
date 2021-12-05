package com.example.fullregistrationform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var insertEmailEditText: EditText
    private lateinit var insertPasswordEditText: EditText
    private lateinit var insertPassword2EditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        userRegister()

    }

    private fun init() {

        insertEmailEditText = findViewById(R.id.insertEmailEditText)
        insertPasswordEditText = findViewById(R.id.insertPasswordEditText)
        insertPassword2EditText = findViewById(R.id.insertPassword2EditText)
        submitButton = findViewById(R.id.submitButton)
    }

    private fun userRegister() {

        submitButton.setOnClickListener {

            val email = insertEmailEditText.text.toString()
            val password = insertPasswordEditText.text.toString()
            val password2 = insertPassword2EditText.text.toString()

            if (email.isEmpty()) {
                insertEmailEditText.error = "please insert an Email"
                return@setOnClickListener
            } else if (password.isEmpty()) {
                insertPasswordEditText.error = "please insert the password"
                return@setOnClickListener
            } else if (password2.isEmpty()) {
                insertPassword2EditText.error = "please repeat the password"
                return@setOnClickListener
            } else if (!email.contains("@")) {
                insertEmailEditText.error = "please insert an Email correctly"
                return@setOnClickListener
            } else if (password.length < 9) {
                insertPasswordEditText.error = "password should contain no less than 9 symbols"
                return@setOnClickListener
            } else if (!password.contains(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!\\-_?&])"))) {
                insertPasswordEditText.error = "password should contain uppercase and lowercase letters, symbols and digits"
                return@setOnClickListener
            } else if (password != password2) {
                insertPassword2EditText.error = "repeated password should be the same"
                return@setOnClickListener
            } else Toast.makeText(this, "success!", Toast.LENGTH_SHORT).show()

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)

        }

    }

}