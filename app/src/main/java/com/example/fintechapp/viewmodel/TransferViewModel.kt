package com.example.fintechapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fintechapp.model.data.Account

class TransferViewModel : ViewModel() {

    var accounts: MutableList<Account> = mutableListOf()

    fun getAccountNames(): List<String> {
        return mutableListOf("Select an account") + accounts.map { it.accountName }
    }

    fun isValidSelection(sourceIndex: Int, destinationIndex: Int): Boolean {
        return sourceIndex > 0 && destinationIndex > 0 && sourceIndex != destinationIndex
    }

    fun isValidAmount(amount: Double?): Boolean {
        return amount != null && amount > 0
    }

    fun hasSufficientBalance(sourceIndex: Int, amount: Double): Boolean {
        return accounts[sourceIndex - 1].balance >= amount
    }

    fun performTransfer(sourceIndex: Int, destinationIndex: Int, amount: Double): Pair<Account, Account> {
        val sourceAccount = accounts[sourceIndex - 1]
        val destinationAccount = accounts[destinationIndex - 1]

        sourceAccount.balance -= amount
        destinationAccount.balance += amount

        return Pair(sourceAccount, destinationAccount)
    }
}
