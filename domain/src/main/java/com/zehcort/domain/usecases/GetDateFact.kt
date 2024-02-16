package com.zehcort.domain.usecases

import com.zehcort.domain.models.DateFact
import com.zehcort.domain.repositories.NumbersRepository
import com.zehcort.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDateFact(
    private val repository: NumbersRepository
) {
    suspend operator fun invoke(day: Int, month: Int): Flow<Resource<DateFact>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getDateFact(day = day, month = month)
            emit(Resource.Success(response))
        } catch (ex: Exception) {
            emit(Resource.Error(message = ex.message!!, stacktrace = ex.stackTrace))
        }
    }
}