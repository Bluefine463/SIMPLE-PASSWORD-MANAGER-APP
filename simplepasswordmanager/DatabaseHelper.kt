package com.example.simplepasswordmanager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "passwords.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE Passwords(id INTEGER PRIMARY KEY AUTOINCREMENT, account TEXT, password TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Passwords")
        onCreate(db)
    }

    fun addAccount(account: String, password: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("account", account)
        values.put("password", password)
        db.insert("Passwords", null, values)
    }

    fun deleteAccount(id: Int) {
        val db = writableDatabase
        db.delete("Passwords", "id=?", arrayOf(id.toString()))
    }

    fun getAllAccounts(): MutableList<Account> {
        val list = mutableListOf<Account>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Passwords", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val account = cursor.getString(1)
                val password = cursor.getString(2)
                list.add(Account(id, account, password))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}

