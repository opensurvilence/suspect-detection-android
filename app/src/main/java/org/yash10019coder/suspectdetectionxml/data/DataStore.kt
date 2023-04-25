package org.yash10019coder.suspectdetectionxml.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

public val AUTH_TOKEN_JWT = stringPreferencesKey("auth_token")

class DataStoreUtil @Inject constructor(
    private val context: Context
) {

    public val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    public suspend fun saveAuthToken(
        authToken: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_TOKEN_JWT] = authToken
        }
    }

    public suspend fun getAuthToken(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[AUTH_TOKEN_JWT]
        }.first()
    }

    public fun getDataStore()
            : DataStore<Preferences> {
        return context.dataStore
    }


    //Return daa
}
