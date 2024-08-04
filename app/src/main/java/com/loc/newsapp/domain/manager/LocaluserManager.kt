package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow


interface LocaluserManager {
    suspend fun saveAppEntry()

    suspend fun readAppEntry() : Flow<Boolean>
}