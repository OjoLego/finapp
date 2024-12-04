package com.example.fintechapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fintechapp.model.data.Account

class AccountViewModel : ViewModel() {

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> get() = _accounts

    init {
        loadInitialAccounts()
    }

    private fun loadInitialAccounts() {
        _accounts.value = listOf(
            Account("1", "Savings Account", "123456789", 5000.00),
            Account("2", "Checking Account", "987654321", 1500.00),
            Account("3", "Business Account", "112233445", 10000.00)
        )
    }

    fun updateAccounts(updatedAccounts: List<Account>) {
        _accounts.value = updatedAccounts
    }
}
