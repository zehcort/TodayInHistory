package com.zehcort.todayinhistory.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zehcort.domain.usecases.GetDateFact
import com.zehcort.domain.utils.Resource
import com.zehcort.todayinhistory.states.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDateFact: GetDateFact
) : ViewModel() {
    private val _homeUiState = mutableStateOf(HomeUiState())
    val homeUiState: State<HomeUiState> = _homeUiState

    fun fetchDateFact(day: Int, month: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getDateFact(day = day, month = month).collect { result ->
                when (result) {
                    is Resource.Error -> _homeUiState.value = homeUiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )

                    is Resource.Loading -> _homeUiState.value = homeUiState.value.copy(
                        isLoading = true
                    )

                    is Resource.Success -> _homeUiState.value = homeUiState.value.copy(
                        isLoading = false,
                        dateFact = result.data
                    )
                }
            }
        }
    }

    fun onSelectedDateChange(newDate: LocalDate) {
        _homeUiState.value = homeUiState.value.copy(
            selectedDate = newDate
        )
    }
}