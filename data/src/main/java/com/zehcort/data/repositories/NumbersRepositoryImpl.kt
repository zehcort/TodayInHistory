package com.zehcort.data.repositories

import com.zehcort.data.datasources.remote.api.numbers.Constants
import com.zehcort.data.datasources.remote.api.numbers.NumbersApi
import com.zehcort.data.dtos.toDomain
import com.zehcort.domain.models.DateFact
import com.zehcort.domain.repositories.NumbersRepository
import javax.inject.Inject

class NumbersRepositoryImpl @Inject constructor(
    private val numbersApi: NumbersApi
) : NumbersRepository {
    override suspend fun getDateFact(day: Int, month: Int): DateFact =
        numbersApi.getDateFact(day = day, month = month, apiKey = Constants.NUMBERS_API_KEY).toDomain()
}