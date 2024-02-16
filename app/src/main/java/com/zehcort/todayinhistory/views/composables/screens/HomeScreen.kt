package com.zehcort.todayinhistory.views.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zehcort.todayinhistory.R
import com.zehcort.todayinhistory.viewmodels.HomeViewModel
import com.zehcort.todayinhistory.views.composables.components.DatePickerDialog
import com.zehcort.todayinhistory.views.composables.components.ErrorContent
import com.zehcort.todayinhistory.views.composables.components.LoadingIndicator
import java.time.LocalDate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val state = viewModel.homeUiState.value
    val errorMessage = state.errorMessage

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var showDatePicker by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clickable { showDatePicker = true },
            value = state.selectedDateText,
            onValueChange = { viewModel.onSelectedDateChange(state.selectedDate ?: LocalDate.now()) },
            readOnly = true,
            enabled = false
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.fetchDateFact(day = state.day, month = state.month) }, enabled = state.isButtonEnabled
        ) {
            Text(text = stringResource(id = R.string.search_fact))
        }

        if (state.isLoading) {
            LoadingIndicator()
        } else if (errorMessage.isNullOrEmpty()) {
            state.dateFact?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    text = it.year, textAlign = TextAlign.Center
                )

                Text(
                    text = it.text,
                    textAlign = TextAlign.Justify
                )
            }
        } else {
            ErrorContent(
                errorMessage = errorMessage
            )
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDateSelected = { viewModel.onSelectedDateChange(it) },
                onDismiss = { showDatePicker = false }
            )
        }
    }
}