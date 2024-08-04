package com.loc.newsapp.domain.usecases.app_entry

import com.loc.newsapp.domain.manager.LocaluserManager

class SaveAppEntry(private val localuserManager: LocaluserManager) {
    suspend operator fun invoke(){
        localuserManager.saveAppEntry()
    }
}