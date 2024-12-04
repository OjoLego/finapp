package com.example.fintechapp.view.screen

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fintechapp.R
import com.example.fintechapp.model.data.Account
import com.example.fintechapp.model.data.Transaction
import com.example.fintechapp.viewmodel.HistoryViewModel
import com.example.fintechapp.viewmodel.TransferViewModel
import java.text.NumberFormat
import java.util.Locale

class TransferActivity : AppCompatActivity() {

    private val viewModel: TransferViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        viewModel.accounts = intent.getParcelableArrayListExtra("accounts") ?: mutableListOf()

        val spinnerSource: Spinner = findViewById(R.id.spinnerSourceAccount)
        val spinnerDestination: Spinner = findViewById(R.id.spinnerDestinationAccount)
        val editTextAmount: EditText = findViewById(R.id.editTextAmount)
        val buttonConfirmTransfer: Button = findViewById(R.id.buttonConfirmTransfer)

        val accountNames = viewModel.getAccountNames()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, accountNames)
        spinnerSource.adapter = arrayAdapter
        spinnerDestination.adapter = arrayAdapter

        editTextAmount.addTextChangedListener(object : TextWatcher {
            private var current = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    editTextAmount.removeTextChangedListener(this)

                    val cleanString = s.toString().replace("[,]".toRegex(), "")
                    if (cleanString.isNotEmpty()) {
                        val parsed = cleanString.toLongOrNull() ?: 0
                        val formatted = NumberFormat.getNumberInstance(Locale.US).format(parsed)

                        current = formatted
                        editTextAmount.setText(formatted)
                        editTextAmount.setSelection(formatted.length)
                    }

                    editTextAmount.addTextChangedListener(this)
                }
            }
        })

        buttonConfirmTransfer.setOnClickListener {
            val sourceIndex = spinnerSource.selectedItemPosition
            val destinationIndex = spinnerDestination.selectedItemPosition
            val amount = editTextAmount.text.toString().replace(",", "").toDoubleOrNull()

            if (sourceIndex > 0 && destinationIndex > 0 && sourceIndex == destinationIndex) {
                Toast.makeText(this, "Source and destination accounts cannot be the same.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!viewModel.isValidSelection(sourceIndex, destinationIndex)) {
                Toast.makeText(this, "Please select valid accounts for both source and destination.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!viewModel.isValidAmount(amount)) {
                Toast.makeText(this, "Enter a valid amount.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!viewModel.hasSufficientBalance(sourceIndex, amount!!)) {
                Toast.makeText(this, "Insufficient balance.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val (sourceAccount, destinationAccount) = viewModel.performTransfer(sourceIndex, destinationIndex, amount)
            showTransferSummaryDialog(sourceAccount, destinationAccount, amount)
        }
    }

    private fun showTransferSummaryDialog(sourceAccount: Account, destinationAccount: Account, amount: Double) {
        val summaryMessage = """
            Source Account: ${sourceAccount.accountName} (${sourceAccount.accountNumber})
            Destination Account: ${destinationAccount.accountName} (${destinationAccount.accountNumber})
            Transfer Amount: $${NumberFormat.getNumberInstance(Locale.US).format(amount)}
        """.trimIndent()

        val dialog = AlertDialog.Builder(this)
            .setTitle("Transfer Summary")
            .setMessage(summaryMessage)
            .setPositiveButton("Confirm") { _, _ ->
                val transaction = Transaction(
                    sourceAccount = sourceAccount.accountName,
                    destinationAccount = destinationAccount.accountName,
                    amount = amount,
                    timestamp = System.currentTimeMillis()
                )
                historyViewModel.insertTransaction(transaction)
                val resultIntent = Intent()
                resultIntent.putParcelableArrayListExtra("updatedAccounts", ArrayList(viewModel.accounts))
                setResult(RESULT_OK, resultIntent)
                finish()
            }
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()

        dialog.show()
    }
}