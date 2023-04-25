package org.yash10019coder.suspectdetectionxml.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.yash10019coder.suspectdetectionxml.data.Api.ApiService
import org.yash10019coder.suspectdetectionxml.data.DataStoreUtil
import org.yash10019coder.suspectdetectionxml.data.LoginDataSource
import org.yash10019coder.suspectdetectionxml.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(
    private val apiService: ApiService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource(apiService)
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
