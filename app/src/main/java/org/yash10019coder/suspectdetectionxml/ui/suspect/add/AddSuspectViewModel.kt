package org.yash10019coder.suspectdetectionxml.ui.suspect.add

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.yash10019coder.suspectdetectionxml.data.AUTH_TOKEN_JWT
import org.yash10019coder.suspectdetectionxml.data.Api.ApiService
import org.yash10019coder.suspectdetectionxml.data.DataStoreUtil
import org.yash10019coder.suspectdetectionxml.data.model.SuspectModel
import org.yash10019coder.suspectdetectionxml.data.model.response.AddSuspectResponseModel
import org.yash10019coder.suspectdetectionxml.data.Result
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddSuspectViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {
    val name = ObservableField("")
    val age = ObservableField(-1)
    val place = ObservableField("")
    val location = ObservableField("")
    val timeUnixTimestamp = ObservableField(-1L)
    val imageBase64 = ObservableField("")
    val gender = ObservableField("")
    val info = ObservableField("")
    val remark = ObservableField("")

    suspend fun addSuspect(): Result<AddSuspectResponseModel> {
        return withContext(Dispatchers.IO) {
            try {
                val suspectModel = SuspectModel(
                    name.get()!!,
                    age.get()!!,
                    gender.get()!!,
                    info.get()!!,
                    location.get()!!,
                    timeUnixTimestamp.get()!!,
                    imageBase64.get()!!,
                    remark.get()!!
                )
                val authToken = runBlocking { dataStoreUtil.getAuthToken() }
                val result = apiService.addSuspect(
                    authToken ?: throw Exception("Null auth token"),
                    suspectModel
                )


                if (result.isSuccessful) {
                    Timber.d("Suspect added successfully")
                    return@withContext Result.Success(result.body()!!)
                } else {
                    Timber.e("Error adding suspect %s", result.errorBody()?.string())
                    return@withContext Result.Error(Exception(result.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Timber.e(e)
                return@withContext Result.Error(e)
            }
        }
    }
}
