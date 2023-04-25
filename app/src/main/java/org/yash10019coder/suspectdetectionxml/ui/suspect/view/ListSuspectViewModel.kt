package org.yash10019coder.suspectdetectionxml.ui.suspect.view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.yash10019coder.suspectdetectionxml.data.Api.ApiService
import org.yash10019coder.suspectdetectionxml.data.DataStoreUtil
import org.yash10019coder.suspectdetectionxml.data.model.response.GetSuspectResponseModel
import timber.log.Timber
import javax.inject.Inject
import org.yash10019coder.suspectdetectionxml.data.Result as Result

@HiltViewModel
class ListSuspectViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {
    suspend fun getSuspectList(): Result<List<GetSuspectResponseModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getSuspects(dataStoreUtil.getAuthToken() ?: "")
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
