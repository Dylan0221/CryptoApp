package com.example.cryptoapp.di.module

import com.example.cryptoapp.data.remote.api_service.CoinGeckoApi
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCoinGeckoApi():CoinGeckoApi =
        Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinGeckoApi): CoinRepository =
        CoinRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.Default)


}