package org.yash10019coder.suspectdetectionxml.ui.suspect.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.yash10019coder.suspectdetectionxml.data.Retrofit
import org.yash10019coder.suspectdetectionxml.data.model.response.GetSuspectResponseModel
import timber.log.Timber
import org.yash10019coder.suspectdetectionxml.data.Result as Result

class ListSuspectViewModel : ViewModel() {
    suspend fun getSuspectList(): Result<List<GetSuspectResponseModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = Retrofit.apiService.getSuspects()
                if (response.isSuccessful) {
                    Timber.d("Suspect list fetched successfully")
                    Result.Success(response.body()!!)
                } else {
                    Timber.e("Error fetching suspect list %s", response.message())
                    Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(e)
            }
        }
    }
}
