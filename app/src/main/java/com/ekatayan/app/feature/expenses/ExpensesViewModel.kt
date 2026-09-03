package com.ekatayan.app.feature.expenses

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.ekatayan.app.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class BudgetSummary(val totalBudget: Long, val totalSpent: Long, val remaining: Long, val usedFraction: Float, val daysRemaining: Int)
data class ExpenseCategory(@StringRes val nameRes: Int, val amount: Long, val percentage: Int, val color: Color, val icon: ImageVector)
data class QuickAction(@StringRes val labelRes: Int, val icon: ImageVector, val tint: Color)
data class Expense(@StringRes val titleRes: Int, @StringRes val categoryRes: Int, val amount: Long, @StringRes val dateTimeRes: Int, val icon: ImageVector, val tint: Color, val participantCount: Int? = null)
data class ExpensesUiState(val budget: BudgetSummary, val categories: List<ExpenseCategory>, val quickActions: List<QuickAction>, val recentExpenses: List<Expense>)

@HiltViewModel
class ExpensesViewModel @Inject constructor() : ViewModel() {
    val uiState = ExpensesUiState(
        BudgetSummary(120_000, 68_450, 51_550, .57f, 8),
        listOf(
            ExpenseCategory(R.string.expenses_category_accommodation, 24_000, 35, Color(0xFF168AF4), Icons.Default.Bed),
            ExpenseCategory(R.string.expenses_category_transport, 16_800, 25, Color(0xFF48C735), Icons.Default.Train),
            ExpenseCategory(R.string.expenses_category_food_drinks, 12_400, 18, Color(0xFFFFA000), Icons.Default.Restaurant),
            ExpenseCategory(R.string.expenses_category_activities, 7_250, 11, Color(0xFF7955D9), Icons.Default.LocalActivity),
            ExpenseCategory(R.string.expenses_category_shopping, 4_500, 7, Color(0xFF00BCD4), Icons.Default.LocalMall),
            ExpenseCategory(R.string.expenses_category_others, 3_500, 4, Color(0xFFFF4F8B), Icons.Default.CardGiftcard),
        ),
        listOf(
            QuickAction(R.string.expenses_action_add, Icons.Default.AddCircleOutline, Color(0xFF168AF4)),
            QuickAction(R.string.expenses_action_split, Icons.Default.Groups, Color(0xFF21B95B)),
            QuickAction(R.string.expenses_action_budget, Icons.Default.Savings, Color(0xFFFF9800)),
            QuickAction(R.string.expenses_action_export, Icons.Default.Download, Color(0xFF7A3FE0)),
        ),
        listOf(
            Expense(R.string.expenses_hotel_kandy, R.string.expenses_category_accommodation, 12_000, R.string.expenses_today_time, Icons.Default.Bed, Color(0xFF168AF4), 2),
            Expense(R.string.expenses_train_tickets, R.string.expenses_category_transport, 5_600, R.string.expenses_yesterday_train_time, Icons.Default.Train, Color(0xFF21B95B), 1),
            Expense(R.string.expenses_lunch_peradeniya, R.string.expenses_category_food_drinks, 2_850, R.string.expenses_yesterday_lunch_time, Icons.Default.Fastfood, Color(0xFFFF8A00), 4),
            Expense(R.string.expenses_sigiriya_tickets, R.string.expenses_category_activities, 3_000, R.string.expenses_sigiriya_time, Icons.Default.LocalActivity, Color(0xFF7A3FE0), 2),
            Expense(R.string.expenses_souvenirs, R.string.expenses_category_shopping, 1_800, R.string.expenses_souvenirs_time, Icons.Default.LocalMall, Color(0xFFFF4081), 1),
        ),
    )
}
