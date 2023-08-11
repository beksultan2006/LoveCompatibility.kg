package com.template.lovecompatibilitykg.di

import android.content.Context
import androidx.room.Room
import com.template.lovecompatibilitykg.retrofit.LoveApi
import com.template.lovecompatibilitykg.room.LoveDao
import com.template.lovecompatibilitykg.room.LoveDatabase
import com.template.lovecompatibilitykg.sharedPreferences.utils.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Author: Dzhaparov Bekmamat
 */
@InstallIn(SingletonComponent::class)
@Module
// В этом модуле используется аннотация @InstallIn(SingletonComponent::class),
// которая указывает, что модуль должен быть установлен в SingletonComponent,
// что означает, что зависимости, предоставленные этим модулем, будут существовать в течение всего
// времени жизни приложения.

// Модуль помечен аннотацией @Module, что указывает, что это модуль Dagger.
class AppModule {
    // Здесь объявляется класс AppModule, который предоставляет зависимости для LoveApi и Preferences.
    @Provides
    fun provideApi(): LoveApi {
        // В методе provideApi() используется Retrofit для создания экземпляра LoveApi.
        // Задается базовый URL и конвертер данных Gson.
        return Retrofit.Builder().baseUrl("https://love-calculator.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(LoveApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): Preferences {
        // В методе provideSharedPreferences() предоставляется экземпляр класса Preferences,
        // который работает с SharedPreferences. Он помечен аннотацией @Singleton, что гарантирует,
        // что будет создан только один экземпляр этого класса.
        return Preferences(context)
        // В аргументе метода provideSharedPreferences() используется аннотация @ApplicationContext,
        // чтобы Dagger предоставил контекст приложения для создания экземпляра Preferences.
    }

    @Provides
    fun provideLoveDatabase(@ApplicationContext context: Context): LoveDatabase {
        return Room.databaseBuilder(context, LoveDatabase::class.java, "love-file")
            .allowMainThreadQueries().build()
    }

    @Provides
    fun provideLoveDao(@ApplicationContext context: Context): LoveDao {
        return provideLoveDatabase(context).loveDao()
    }
// Оба метода provideApi() и provideSharedPreferences() помечены аннотацией @Provides,
// что указывает Dagger, как предоставить экземпляры этих классов при необходимости.
}
// В целом, этот модуль предоставляет зависимости для работы с API (LoveApi)
// и для доступа к SharedPreferences (Preferences). Эти зависимости могут быть внедрены в другие классы
// с помощью аннотации @Inject, когда необходимо использовать их в приложении.