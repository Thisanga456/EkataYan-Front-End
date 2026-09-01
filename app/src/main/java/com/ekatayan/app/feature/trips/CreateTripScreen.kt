package com.ekatayan.app.feature.trips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import com.ekatayan.app.R
import com.ekatayan.app.core.designsystem.theme.EkataBackground
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTripScreen(onBackClick: () -> Unit, onSave: (String, String, LocalDate, LocalDate, String, String) -> Unit, modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var destination by rememberSaveable { mutableStateOf("") }
    var budget by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }
    var startDateMillis by rememberSaveable { mutableStateOf<Long?>(null) }
    var endDateMillis by rememberSaveable { mutableStateOf<Long?>(null) }
    var pickingStart by rememberSaveable { mutableStateOf(true) }
    var showPicker by rememberSaveable { mutableStateOf(false) }
    var error by rememberSaveable { mutableStateOf<String?>(null) }
    val formatter = DateTimeFormatter.ofPattern("d MMM yyyy")
    val startDate = startDateMillis?.let(::dateFromPickerMillis)
    val endDate = endDateMillis?.let(::dateFromPickerMillis)
    val nameRequiredError = stringResource(R.string.create_trip_name_required)
    val destinationRequiredError = stringResource(R.string.create_trip_destination_required)
    val datesRequiredError = stringResource(R.string.create_trip_dates_required)
    val dateRangeError = stringResource(R.string.create_trip_date_range_invalid)
    val budgetError = stringResource(R.string.create_trip_budget_invalid)

    Column(modifier.fillMaxSize().background(EkataBackground).verticalScroll(rememberScrollState()).padding(20.dp)) {
        Row { IconButton(onClick = onBackClick) { Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.create_trip_back)) }; Text(stringResource(R.string.create_trip_title), style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 12.dp)) }
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(name, { name = it }, label = { Text(stringResource(R.string.create_trip_name)) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(destination, { destination = it }, label = { Text(stringResource(R.string.create_trip_destination)) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            DateField(
                value = startDate?.format(formatter).orEmpty(),
                label = stringResource(R.string.create_trip_start_date),
                onClick = { pickingStart = true; showPicker = true },
                modifier = Modifier.weight(1f),
            )
            DateField(
                value = endDate?.format(formatter).orEmpty(),
                label = stringResource(R.string.create_trip_end_date),
                onClick = { pickingStart = false; showPicker = true },
                modifier = Modifier.weight(1f),
            )
        }
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(budget, { budget = it }, label = { Text(stringResource(R.string.create_trip_budget)) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(notes, { notes = it }, label = { Text(stringResource(R.string.create_trip_notes)) }, modifier = Modifier.fillMaxWidth(), minLines = 3)
        error?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp)) }
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            val validationError = when {
                name.isBlank() -> nameRequiredError
                destination.isBlank() -> destinationRequiredError
                startDate == null || endDate == null -> datesRequiredError
                startDate.isAfter(endDate) -> dateRangeError
                budget.isNotBlank() && budget.toDoubleOrNull() == null -> budgetError
                else -> null
            }
            error = validationError
            if (validationError == null) {
                onSave(name.trim(), destination.trim(), startDate!!, endDate!!, budget.trim(), notes.trim())
            }
        }, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.create_trip_save)) }
    }
    if (showPicker) {
        val state = rememberDatePickerState(
            initialSelectedDateMillis =
                if (pickingStart) startDateMillis else endDateMillis
        )

        DatePickerDialog(
            onDismissRequest = { showPicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        state.selectedDateMillis?.let { millis ->
                            val selected = dateFromPickerMillis(millis)

                            if (!pickingStart && startDate != null && selected.isBefore(startDate)) {
                                error = dateRangeError
                            } else {
                                if (pickingStart) {
                                    startDateMillis = millis

                                    if (endDate != null && selected.isAfter(endDate)) {
                                        endDateMillis = null
                                    }
                                } else {
                                    endDateMillis = millis
                                }

                                error = null
                                showPicker = false
                            }
                        }
                    }
                ) {
                    Text(stringResource(R.string.create_trip_ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showPicker = false }
                ) {
                    Text(stringResource(R.string.create_trip_cancel))
                }
            }
        ) {
            DatePicker(state)
        }
    }
}

@Composable
private fun DateField(
    value: String,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.clickable(onClick = onClick)) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private fun dateFromPickerMillis(millis: Long): LocalDate =
    Instant.ofEpochMilli(millis).atZone(TimeZone.getTimeZone("UTC").toZoneId()).toLocalDate()
