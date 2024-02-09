package com.personalsprojects.androidcompose.di

import com.personalsprojects.androidcompose.data.Repository
import com.personalsprojects.androidcompose.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    //Add singletone to singletone repository
    @Provides
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository {
        return repositoryImpl
    }

}


