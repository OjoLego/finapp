package com.example.fintechapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.fintechapp.model.data.Account
import com.example.fintechapp.viewmodel.AccountViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class AccountViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AccountViewModel

    @Before
    fun setUp() {
        viewModel = AccountViewModel()
    }

    @Test
    fun test_initialLoad_shouldHaveInitialAccounts() {
        // Create an observer to observe LiveData
        val observer = mock(Observer::class.java) as Observer<List<Account>>

        // Observe the LiveData to get the initial value
        viewModel.accounts.observeForever(observer)

        // Verify the observer receives the initial list of accounts
        val expectedAccounts = listOf(
            Account("1", "Savings Account", "123456789", 5000.00),
            Account("2", "Checking Account", "987654321", 1500.00),
            Account("3", "Business Account", "112233445", 10000.00)
        )

        verify(observer).onChanged(expectedAccounts)

        // Clean up observer after use
        viewModel.accounts.removeObserver(observer)
    }

    @Test
    fun test_updateAccounts_shouldUpdateLiveData() {
        // Create a new list of accounts for the update
        val updatedAccounts = listOf(
            Account("4", "New Savings Account", "111122223", 7000.00),
            Account("5", "New Checking Account", "333344445", 2500.00)
        )

        // Update the LiveData using the updateAccounts method
        viewModel.updateAccounts(updatedAccounts)

        // Create an observer to observe LiveData
        val observer = mock(Observer::class.java) as Observer<List<Account>>
        viewModel.accounts.observeForever(observer)

        // Verify the LiveData value is updated
        verify(observer).onChanged(updatedAccounts)

        // Clean up observer after use
        viewModel.accounts.removeObserver(observer)
    }
}
