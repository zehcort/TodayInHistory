package com.zehcort.data.di

import com.squareup.moshi.Moshi
import com.zehcort.data.datasources.remote.api.numbers.Constants
import com.zehcort.data.datasources.remote.api.numbers.NumbersApi
import com.zehcort.data.repositories.NumbersRepositoryImpl
import com.zehcort.domain.repositories.NumbersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        moshi: Moshi
    ): Retrofit = Retrofit.Builder().baseUrl(Constants.NUMBERS_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()

    @Provides
    @Singleton
    fun providerNumbersApi(retrofit: Retrofit): NumbersApi = retrofit.create(NumbersApi::class.java)

    @Provides
    @Singleton
    fun provideNumbersRepository(numbersApi: NumbersApi): NumbersRepository =
        NumbersRepositoryImpl(numbersApi = numbersApi)
}