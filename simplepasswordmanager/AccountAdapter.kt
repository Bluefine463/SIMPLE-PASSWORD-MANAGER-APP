package com.example.simplepasswordmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible

class AccountAdapter(
    private val context: Context,
    private var accounts: MutableList<Account>,
    private val dbHelper: DatabaseHelper
) : BaseAdapter() {

    fun updateAccounts(newAccounts: MutableList<Account>) {
        accounts = newAccounts
        notifyDataSetChanged()
    }

    override fun getCount(): Int = accounts.size

    override fun getItem(position: Int): Any = accounts[position]

    override fun getItemId(position: Int): Long = accounts[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.account_item, parent, false)

        val accountTitle = view.findViewById<TextView>(R.id.tvAccountName)
        val passwordText = view.findViewById<TextView>(R.id.tvPassword)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)

        val account = accounts[position]
        accountTitle.text = account.accountName
        passwordText.text = account.password
        passwordText.isVisible = account.isExpanded

        accountTitle.setOnClickListener {
            account.isExpanded = !account.isExpanded
            notifyDataSetChanged()
        }

        btnDelete.setOnClickListener {
            dbHelper.deleteAccount(account.id)
            accounts.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }
}
