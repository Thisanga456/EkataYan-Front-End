package com.ekatayan.app.feature.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekatayan.app.R
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Duration
import java.time.LocalDate
import java.time.YearMonth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(createTripsUiState(LocalDate.now()))
    val uiState: StateFlow<TripsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            while (isActive) {
                val today = LocalDate.now()
                _uiState.update { state -> if (state.today == today) state else state.copy(today = today) }
                val nextDay = today.plusDays(1).atStartOfDay()
                delay(Duration.between(java.time.LocalDateTime.now(), nextDay).toMillis().coerceAtLeast(1L))
            }
        }
    }

    fun showPreviousMonth() {
        _uiState.update { it.copy(displayedMonth = it.displayedMonth.minusMonths(1)) }
    }

    fun showNextMonth() {
        _uiState.update { it.copy(displayedMonth = it.displayedMonth.plusMonths(1)) }
    }
}

data class TripsUiState(val displayedMonth: YearMonth, val today: LocalDate, val trips: List<Trip>)

data class Trip(
    val id: Int,
    val nameRes: Int,
    val locationRes: Int,
    val statusRes: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val imageRes: Int,
)

private fun createTripsUiState(today: LocalDate) = TripsUiState(
    displayedMonth = YearMonth.from(today),
    today = today,
    trips = listOf(
        Trip(1, R.string.trip_galle_name, R.string.trip_galle_location, R.string.trip_status_upcoming, today.plusDays(3), today.plusDays(5), R.drawable.galle),
        Trip(2, R.string.trip_kandy_name, R.string.trip_kandy_location, R.string.trip_status_planned, today.plusDays(12), today.plusDays(15), R.drawable.kandy),
        Trip(3, R.string.trip_sigiriya_name, R.string.trip_sigiriya_location, R.string.trip_status_planned, today.plusDays(25), today.plusDays(27), R.drawable.sigiriya),
        Trip(4, R.string.trip_nuwara_eliya_name, R.string.trip_nuwara_eliya_location, R.string.trip_status_planned, today.plusDays(40), today.plusDays(43), R.drawable.nine_arch_bridge),
    ),
)

internal fun calendarMonthGrid(month: YearMonth): List<LocalDate?> {
    val leadingEmptyDays = month.atDay(1).dayOfWeek.value - 1
    return List(42) { index ->
        val day = index - leadingEmptyDays + 1
        if (day in 1..month.lengthOfMonth()) month.atDay(day) else null
    }
}
