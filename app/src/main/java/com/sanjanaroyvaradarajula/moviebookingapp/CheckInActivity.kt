package com.sanjanaroyvaradarajula.moviebookingapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.database.FirebaseDatabase
import com.sanjanaroyvaradarajula.moviebookingapp.moviesModule.MovieFanData

class CheckInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenP()
{
    LoginScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var passwordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }


    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.first))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            // Heart Icon
            Icon(
                painter = painterResource(id = R.drawable.baseline_movie_24), // Replace with your icon
                contentDescription = "Heart Icon",
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Welcome Text
            Text(
                text = "Welcome Back!",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Login to your account...",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.height(32.dp))


            // Green Box containing email, password, and controls
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        colorResource(id = R.color.second),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Column {
                    // Email or Username label
                    Text(
                        text = "Email or Username",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )


                    // Email TextField
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Enter your Email here") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email Icon"
                            )
                        },

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password label
                    Text(
                        text = "Password",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )


                    // Password TextField
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Enter your password") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password Icon"
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Remember Me and Forgot Password Row
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            Checkbox(checked = false, onCheckedChange = {})
//                            Text(text = "Remember me", color = Color.White)
//                        }
//                        Text(
//                            text = "Forgot Password?",
//                            color = Color.White,
//                            style = MaterialTheme.typography.bodySmall
//                        )
//                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Sign In Button
                    Button(
                        onClick = {
                            when {


                                email.isBlank() -> {
                                    errorMessage = "Please enter your email."
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }

                                password.isBlank() -> {
                                    errorMessage = "Please enter your password."
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }


                                else -> {

                                    val movieFan = MovieFan(
                                        mail = email,
                                        password = password
                                    )
                                    CheckInToBooking(movieFan, context)


                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("Sign in")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Register Text
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account? ",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Register Now",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.clickable {
                        context.startActivity(Intent(context, RegisterActivity::class.java))
                        (context as Activity).finish()
                    }
                )
            }
        }
    }
}

data class MovieFan(
    var fullname : String = "",
    var mail : String = "",
    var password : String = "",
    var city : String = ""
)

fun CheckInToBooking(movieFan: MovieFan, context: Context) {


        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.getReference("MovieFanDetails").child(movieFan.mail.replace(".", ","))

        databaseReference.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val fanData = task.result?.getValue(MovieFan::class.java)
                if (fanData != null) {
                    if (fanData.password == movieFan.password) {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        MovieFanData.persistLoginState(context, true)
                        MovieFanData.persistUserMail(context, fanData.mail)
                        MovieFanData.persistUserName(context, fanData.fullname)
                        context.startActivity(Intent(context, HomeActivity::class.java))
                        (context as Activity).finish()
                    } else {
                        Toast.makeText(context, "Seems Incorrect Credentials", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Your account not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    context,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }
}