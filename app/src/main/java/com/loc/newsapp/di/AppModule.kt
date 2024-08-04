package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manager.LocalUserManagerImpl
import com.loc.newsapp.data.remote.NewsAPI
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.manager.LocaluserManager
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.loc.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.loc.newsapp.domain.usecases.news.GetNews
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.domain.usecases.news.SearchNews
import com.loc.newsapp.utils.Constants.BASE_URL
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
    fun provideLocalUserManage(
        application: Application
    ) : LocaluserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localuserManager: LocaluserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localuserManager),
        saveAppEntry = SaveAppEntry(localuserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsAPI: NewsAPI
    ) : NewsRepository {
        return NewsRepositoryImpl(newsAPI)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
      newsRepository: NewsRepository
    ) : NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }
}