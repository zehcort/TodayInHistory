package com.zehcort.todayinhistory.views.composables.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zehcort.todayinhistory.R
import com.zehcort.todayinhistory.utils.toDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val currentDate = LocalDate.now()
    val datePickerState = rememberDatePickerState(yearRange = IntRange(currentDate.year, currentDate.year))
    val selectedDate = datePickerState.selectedDateMillis?.toDate() ?: LocalDate.now()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            }

            ) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}