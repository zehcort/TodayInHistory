package com.zehcort.data.datasources.remote.api.numbers

import com.zehcort.data.entities.remote.numbers.DateFact
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface NumbersApi {
    @GET("{month}/{day}/date?json=true")
    suspend fun getDateFact(
        @Path(value = "day", encoded = true) day: Int,
        @Path(value = "month", encoded = true) month: Int,
        @Header("X-RapidAPI-Key") apiKey: String
    ): DateFact
}