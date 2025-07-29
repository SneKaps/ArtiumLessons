package com.example.artiumlessons.di

import com.example.artiumlessons.data.remote.ApiService
import com.example.artiumlessons.data.repository.LessonsRepository
import com.example.artiumlessons.data.repository.LessonsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.jsonkeeper.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLessonsRepository(apiService: ApiService): LessonsRepository {
        return LessonsRepositoryImpl(apiService)
    }
}
