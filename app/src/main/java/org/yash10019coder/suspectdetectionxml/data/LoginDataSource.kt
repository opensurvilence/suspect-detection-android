package org.yash10019coder.suspectdetectionxml.data

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.yash10019coder.suspectdetectionxml.data.Api.ApiService
import org.yash10019coder.suspectdetectionxml.data.model.LoggedInUser
import org.yash10019coder.suspectdetectionxml.data.model.LoginModel
import timber.log.Timber
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return withContext(Dispatchers.IO) {
            try {
                val login = apiService.login(LoginModel(username, password))
                if (login.isSuccessful) {
                    Timber.d("Login successful")
                    val user = LoggedInUser(
                        UUID.randomUUID().toString(),
                        username,
                        login.body()?.accessToken ?: ""
                    )
                    Timber.d("User: %s", user.toString())
                    return@withContext Result.Success(user)
                } else {
                    val errorResModel = login.errorBody()?.string()
                    Timber.e("Error logging in: %s", errorResModel)
                    return@withContext Result.Error(IOException(errorResModel))
                }
            } catch (e: Throwable) {
                Timber.e(e)
                return@withContext Result.Error(IOException("Error logging in", e))
            }
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}
