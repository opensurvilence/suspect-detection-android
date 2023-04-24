package org.yash10019coder.suspectdetectionxml.ui.suspect.add

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.yash10019coder.suspectdetectionxml.data.Retrofit
import org.yash10019coder.suspectdetectionxml.data.model.SuspectModel
import org.yash10019coder.suspectdetectionxml.data.model.response.AddSuspectResponseModel
import org.yash10019coder.suspectdetectionxml.data.Result
import timber.log.Timber

class AddSuspectViewModel : ViewModel() {
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
                val result = Retrofit.apiService.addSuspect(suspectModel)

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
