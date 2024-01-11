import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tahfizhhub.R
import com.example.tahfizhhub.navigasi.DestinasiNavigasi

object DestinasiLogin : DestinasiNavigasi {
    override val route = "login"
    override val titleRes = "Login"
}
@Composable
fun LoginScreen(
    navigateToHomePage: () -> Unit,
    onNavToSignUpPage: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val isLoggedIn by rememberUpdatedState(newValue = viewModel.isLoggedIn)

    if (isLoggedIn) {
        navigateToHomePage.invoke()
    } else {
        LoginContent(
            onLoginClick = { email, password ->
                viewModel.login(email, password)
            },
            onSignUpClick = onNavToSignUpPage
        )
    }
}

@Composable
fun LoginContent(
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.quran),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoginForm(
            onLoginClick = onLoginClick,
            onSignUpClick = onSignUpClick
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginForm(
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* Handle next action */ }
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onLoginClick.invoke(email, password)
                    keyboardController?.hide()
                }
            )
        )

        Button(onClick = { onLoginClick.invoke(email, password) }) {
            Text("Login")
        }

        TextButton(onClick = { onSignUpClick.invoke() }) {
            Text("Don't have an account? Sign Up")
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginContent(
        onLoginClick = { _, _ -> },
        onSignUpClick = {}
    )
}
