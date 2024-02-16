package com.zehcort.todayinhistory

import com.squareup.moshi.Moshi
import com.zehcort.data.datasources.remote.api.numbers.Constants
import com.zehcort.data.datasources.remote.api.numbers.NumbersApi
import com.zehcort.data.repositories.NumbersRepositoryImpl
import com.zehcort.domain.repositories.NumbersRepository
import com.zehcort.domain.usecases.GetDateFact
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator {
    fun provideGetDateFactUseCase(): GetDateFact =
        GetDateFact(repository = provideNumbersRepository(provideNumbersApi(provideRetrofitInstance(provideMoshi()))))

    private fun provideMoshi(): Moshi = Moshi.Builder().build()

    private fun provideRetrofitInstance(
        moshi: Moshi
    ): Retrofit = Retrofit.Builder().baseUrl(Constants.NUMBERS_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    private fun provideNumbersApi(retrofit: Retrofit): NumbersApi = retrofit.create(NumbersApi::class.java)

    private fun provideNumbersRepository(numbersApi: NumbersApi): NumbersRepository =
        NumbersRepositoryImpl(numbersApi = numbersApi)
}