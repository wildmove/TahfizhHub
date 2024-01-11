import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.compose.runtime.*

class LoginViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    var isLoggedIn by mutableStateOf(false)
        private set

    init {
        checkLoginStatus()
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isLoggedIn = true
                } else {
                    // Handle login failure
                    isLoggedIn = false
                }
            }
    }

    private fun checkLoginStatus() {
        firebaseAuth.addAuthStateListener { auth ->
            isLoggedIn = auth.currentUser != null
        }
    }
}

