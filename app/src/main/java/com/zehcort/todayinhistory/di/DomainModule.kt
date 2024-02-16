package com.zehcort.todayinhistory.di

import com.zehcort.domain.repositories.NumbersRepository
import com.zehcort.domain.usecases.GetDateFact
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetDateFactUseCase(repository: NumbersRepository): GetDateFact = GetDateFact(repository = repository)
}