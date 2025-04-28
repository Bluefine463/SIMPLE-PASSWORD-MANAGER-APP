package com.example.simplepasswordmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddAccountActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        dbHelper = DatabaseHelper(this)

        val etAccount = findViewById<EditText>(R.id.etAccountName)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSave = findViewById<Button>(R.id.btnSaveAccount)

        btnSave.setOnClickListener {
            val accountName = etAccount.text.toString()
            val password = etPassword.text.toString()

            if (accountName.isNotEmpty() && password.isNotEmpty()) {
                dbHelper.addAccount(accountName, password)
                Toast.makeText(this, "Account Added!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

