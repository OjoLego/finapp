package com.example.fintechapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val id: String,
    val accountName: String,
    val accountNumber: String,
    var balance: Double
) : Parcelable
