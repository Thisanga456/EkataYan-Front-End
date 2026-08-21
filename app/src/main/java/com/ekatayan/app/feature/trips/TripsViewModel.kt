package com.ekatayan.app.feature.trips

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TripsViewModel @Inject constructor() : ViewModel() {
    val title = "Trips"
}
