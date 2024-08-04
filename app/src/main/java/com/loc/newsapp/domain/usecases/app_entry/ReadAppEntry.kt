package com.loc.newsapp.domain.usecases.app_entry

import com.loc.newsapp.domain.manager.LocaluserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (private val localuserManager: LocaluserManager) {
    suspend operator fun invoke() : Flow<Boolean> {
        return localuserManager.readAppEntry()
    }
}