package com.example.fintechapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fintechapp.R
import com.example.fintechapp.model.data.Transaction
import java.text.NumberFormat
import java.util.Locale

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sourceAccount: TextView = view.findViewById(R.id.textSourceAccount)
        val destinationAccount: TextView = view.findViewById(R.id.textDestinationAccount)
        val amount: TextView = view.findViewById(R.id.textAmount)
        val timestamp: TextView = view.findViewById(R.id.textTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        val formattedBalance = NumberFormat.getCurrencyInstance(Locale.US).format(transaction.amount)
        holder.sourceAccount.text = "From: ${transaction.sourceAccount}"
        holder.destinationAccount.text = "To: ${transaction.destinationAccount}"
        holder.amount.text = formattedBalance
        holder.timestamp.text = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .format(java.util.Date(transaction.timestamp))
    }

    override fun getItemCount(): Int = transactions.size
}
