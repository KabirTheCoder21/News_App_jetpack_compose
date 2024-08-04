package com.loc.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.manager.LocaluserManager
import com.loc.newsapp.utils.Constants
import com.loc.newsapp.utils.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(private val context: Context): LocaluserManager {
    override suspend fun saveAppEntry() {
       context.dataStore.edit {
           it[PreferencesKey.APP_ENTRY] = true
       }
    }

    override suspend fun readAppEntry(): Flow<Boolean> {
       return context.dataStore.data.map {
            it[PreferencesKey.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)
private object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)

}