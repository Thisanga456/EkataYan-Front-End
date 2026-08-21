package com.ekatayan.app.feature.expenses

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor() : ViewModel() {
    val title = "Expenses"
}
