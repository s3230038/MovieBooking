package movie.bookingappbysanjanaroyvaradarajula.s3230038

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import movie.bookingappbysanjanaroyvaradarajula.s3230038.moviesModule.MovieFanData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieLaunchCheck()
        }
    }
}


@Composable
fun MovieLaunchCheck() {
    val context = LocalContext.current as Activity
    var showSplash by remember { mutableStateOf(true) }


    DisposableEffect(Unit) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            showSplash = false
        }
        onDispose { job.cancel() }
    }

    if (showSplash) {
        MovieLaunch()

    } else {
        val fanStatus = MovieFanData.fetchLoginState(context)

        if (fanStatus) {
            context.startActivity(Intent(context, HomeActivity::class.java))
            context.finish()
        } else {
            context.startActivity(Intent(context, CheckInActivity::class.java))
            context.finish()
        }
    }

}


@Composable
fun MovieLaunch() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color(0xFF101C64)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sanjanaroy varadarajula",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Movie Booking App",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.movie_booking),
                contentDescription = "Sanjanaroy varadarajula Movie Booking App",
            )
        }
    }

}
