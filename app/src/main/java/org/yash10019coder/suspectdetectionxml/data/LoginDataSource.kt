package org.yash10019coder.suspectdetectionxml.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.yash10019coder.suspectdetectionxml.data.model.LoggedInUser
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return withContext(Dispatchers.IO) {
            try {
                val login = Retrofit.apiService.login(username, password)
                if (login.isSuccessful) {
                    val user = LoggedInUser(
                        UUID.randomUUID().toString(),
                        username,
                        login.body()?.accessToken ?: ""
                    )
                    return@withContext Result.Success(user)
                } else {
                    val errorResModel = login.errorBody()?.string()
                    return@withContext Result.Error(IOException(errorResModel))
                }
            } catch (e: Throwable) {
                return@withContext Result.Error(IOException("Error logging in", e))
            }
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}
