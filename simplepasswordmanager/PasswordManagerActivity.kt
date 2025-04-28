package com.example.simplepasswordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PasswordManagerActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var accountAdapter: AccountAdapter
    private lateinit var accountListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_manager)

        dbHelper = DatabaseHelper(this)
        accountListView = findViewById(R.id.accountListView)

        val accounts = dbHelper.getAllAccounts()
        accountAdapter = AccountAdapter(this, accounts, dbHelper)
        accountListView.adapter = accountAdapter

        val btnAdd = findViewById<Button>(R.id.btnAddAccount)
        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddAccountActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val accounts = dbHelper.getAllAccounts()
        accountAdapter.updateAccounts(accounts)
    }
}

