package com.example.queueless.ui.dashboard.dashboard_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.queueless.data.remote.dto.dashboard.Businesse
import com.example.queueless.data.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private  val repository: DashboardRepository
): ViewModel() {
    /* ---------------- EXISTING STATES (UNCHANGED) ---------------- */

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _listOfBusinesses = MutableStateFlow<List<Businesse>>(emptyList())
    val listOfBusinesses: StateFlow<List<Businesse>> = _listOfBusinesses


    fun getAllBusinesses() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = repository.getAllBusinesses()

                if (response.success) {
                    _listOfBusinesses.value = response.businesses
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

}