package com.zehcort.domain.repositories

import com.zehcort.domain.models.DateFact

interface NumbersRepository {
    suspend fun getDateFact(day: Int, month: Int): DateFact
}