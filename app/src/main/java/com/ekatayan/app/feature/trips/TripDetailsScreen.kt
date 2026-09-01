package com.ekatayan.app.feature.trips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ekatayan.app.R
import com.ekatayan.app.core.designsystem.theme.EkataBackground
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TripDetailsRoute(
    tripId: Int?,
    onBackClick: () -> Unit,
    viewModel: TripsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val trip = state.trips.firstOrNull { it.id == tripId }
    Column(Modifier.fillMaxSize().background(EkataBackground).padding(20.dp)) {
        Row {
            IconButton(onClick = onBackClick) { Icon(Icons.Default.ArrowBack, stringResource(R.string.create_trip_back)) }
            Text(trip?.displayName() ?: stringResource(R.string.trip_details_title), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 12.dp))
        }
        Spacer(Modifier.height(24.dp))
        if (trip == null) {
            Text(stringResource(R.string.trip_details_missing))
        } else {
            Text(
                "${trip.startDate.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.getDefault()))} - ${trip.endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.getDefault()))}",
                color = com.ekatayan.app.core.designsystem.theme.EkataBlue,
            )
            Spacer(Modifier.height(12.dp))
            Text(trip.customLocation ?: stringResource(trip.locationRes), style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            Text(stringResource(trip.statusFor(state.today)))
            trip.notes?.takeIf { it.isNotBlank() }?.let { notes ->
                Spacer(Modifier.height(20.dp))
                Text(notes)
            }
        }
    }
}

private fun Trip.displayName(): String = customName ?: "Trip"
