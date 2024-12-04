package com.example.fintechapp.view.screen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintechapp.R
import com.example.fintechapp.view.adapter.TransactionAdapter
import com.example.fintechapp.viewmodel.HistoryViewModel

class TransactionHistoryActivity : AppCompatActivity() {

    private val transactionViewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTransactions)
        val textViewNoTransactions: TextView = findViewById(R.id.noHistoryText)
        recyclerView.layoutManager = LinearLayoutManager(this)

        transactionViewModel.getAllTransactions().observe(this) { transactions ->
            if (transactions.isNullOrEmpty()) {
                recyclerView.visibility = RecyclerView.GONE
                textViewNoTransactions.visibility = TextView.VISIBLE
            } else {
                recyclerView.visibility = RecyclerView.VISIBLE
                textViewNoTransactions.visibility = TextView.GONE
                val adapter = TransactionAdapter(transactions)
                recyclerView.adapter = adapter
            }
        }
    }
}
