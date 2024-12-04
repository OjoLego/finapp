package com.example.fintechapp.view.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintechapp.R
import com.example.fintechapp.model.data.Account
import com.example.fintechapp.view.adapter.AccountAdapter
import com.example.fintechapp.viewmodel.AccountViewModel

class AccountActivity : AppCompatActivity() {

    private val accountViewModel: AccountViewModel by viewModels()
    private lateinit var adapter: AccountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val recyclerView: RecyclerView = findViewById(R.id.accountsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AccountAdapter(emptyList())
        recyclerView.adapter = adapter

        accountViewModel.accounts.observe(this, Observer { accounts ->
            adapter.updateAccounts(accounts)
        })

        val buttonTransfer: Button = findViewById(R.id.transferButton)
        buttonTransfer.setOnClickListener {
            val accounts = accountViewModel.accounts.value ?: return@setOnClickListener
            val intent = Intent(this, TransferActivity::class.java)
            intent.putParcelableArrayListExtra("accounts", ArrayList(accounts))
            transferActivityLauncher.launch(intent)
        }

        val buttonHistory: Button = findViewById(R.id.historyButton)
        buttonHistory.setOnClickListener {
            startActivity(Intent(this, TransactionHistoryActivity::class.java))
        }
    }

    private val transferActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val updatedAccounts =
                    result.data?.getParcelableArrayListExtra<Account>("updatedAccounts")
                if (updatedAccounts != null) {
                    accountViewModel.updateAccounts(updatedAccounts)
                }
            }
        }
}