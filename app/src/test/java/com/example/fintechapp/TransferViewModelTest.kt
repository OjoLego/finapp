package com.example.fintechapp

import com.example.fintechapp.model.data.Account
import com.example.fintechapp.viewmodel.TransferViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TransferViewModelTest {

    private lateinit var viewModel: TransferViewModel

    @Before
    fun setUp() {
        viewModel = TransferViewModel()
        viewModel.accounts = mutableListOf(
            Account(id = "1", accountName = "Account 1", accountNumber = "123456", balance = 1000.0),
            Account(id = "2",accountName = "Account 2", accountNumber = "654321", balance = 2000.0)
        )
    }

    @Test
    fun `test getAccountNames returns list with 'Select an account'`() {
        val accountNames = viewModel.getAccountNames()
        assertEquals(3, accountNames.size)
        assertEquals("Select an account", accountNames[0])
    }

    @Test
    fun `test isValidSelection returns false for same source and destination`() {
        val result = viewModel.isValidSelection(1, 1)
        assertFalse(result)
    }

    @Test
    fun `test isValidSelection returns true for valid selection`() {
        val result = viewModel.isValidSelection(1, 2)
        assertTrue(result)
    }

    @Test
    fun `test isValidAmount returns false for null or negative`() {
        assertFalse(viewModel.isValidAmount(null))
        assertFalse(viewModel.isValidAmount(-10.0))
    }

    @Test
    fun `test isValidAmount returns true for positive amount`() {
        assertTrue(viewModel.isValidAmount(10.0))
    }

    @Test
    fun `test hasSufficientBalance returns false for insufficient funds`() {
        val result = viewModel.hasSufficientBalance(1, 2000.0)
        assertFalse(result)
    }

    @Test
    fun `test hasSufficientBalance returns true for sufficient funds`() {
        val result = viewModel.hasSufficientBalance(1, 500.0)
        assertTrue(result)
    }

    @Test
    fun `test performTransfer updates account balances correctly`() {
        val (source, destination) = viewModel.performTransfer(1, 2, 500.0)
        assertEquals(500.0, source.balance, 0.01)
        assertEquals(2500.0, destination.balance, 0.01)
    }
}
