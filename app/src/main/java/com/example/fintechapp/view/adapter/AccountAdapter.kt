package com.example.fintechapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintechapp.R
import com.example.fintechapp.model.data.Account
import java.text.NumberFormat
import java.util.Locale

class AccountAdapter(private var accounts: List<Account>) :
    RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val accountNameTextView: TextView = itemView.findViewById(R.id.accountNameTextView)
        val accountNumberTextView: TextView = itemView.findViewById(R.id.accountNumberTextView)
        val accountBalanceTextView: TextView = itemView.findViewById(R.id.accountBalanceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_account, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accounts[position]
        val formattedBalance = NumberFormat.getCurrencyInstance(Locale.US).format(account.balance)
        holder.accountNameTextView.text = account.accountName
        holder.accountNumberTextView.text = account.accountNumber
        holder.accountBalanceTextView.text = formattedBalance
    }

    override fun getItemCount() = accounts.size

    fun updateAccounts(newAccounts: List<Account>) {
        accounts = newAccounts
        notifyDataSetChanged()
    }
}
