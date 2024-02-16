package com.zehcort.todayinhistory.states

import com.zehcort.domain.models.DateFact
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedDate: LocalDate? = null,
    val dateFact: DateFact? = null
) {
    val selectedDateText: String
        get() =
            if (selectedDate != null) {
                selectedDate.format(DateTimeFormatter.ofPattern("MMMM d"))
            } else {
                ""
            }

    val day: Int get() = selectedDate?.dayOfMonth ?: LocalDate.now().dayOfMonth

    val month: Int get() = selectedDate?.monthValue ?: LocalDate.now().monthValue

    val isButtonEnabled get() = selectedDate != null
}