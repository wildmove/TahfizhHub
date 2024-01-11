package com.example.tahfizhhub.ui.login




import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.tahfizhhub.repository.LoginRepository
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    data class Error(val message: String) : LoginResult()
}

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _emailState = mutableStateOf("")
    val emailState: MutableState<String> = _emailState

    private val _passwordState = mutableStateOf("")
    val passwordState: MutableState<String> = _passwordState

    private val _loginState: MutableStateFlow<LoginResult?> = MutableStateFlow(null)
    val loginState: StateFlow<LoginResult?> = _loginState.asStateFlow()

    fun onEmailChange(email: String) {
        _emailState.value = email
    }

    fun onPasswordChange(password: String) {
        _passwordState.value = password
    }

    @SuppressLint("RestrictedApi")
    suspend fun loginUser() {
        val email = _emailState.value
        val password = _passwordState.value

        //_loginState.value = LoginResult.Success(User("id", "wildan@mail.id")) // Placeholder loading state

        // Panggil metode loginUser di LoginRepository
        _loginState.value = loginRepository.loginUser(email, password)
    }
}