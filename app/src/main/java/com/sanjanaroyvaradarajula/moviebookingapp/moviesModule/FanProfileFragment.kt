package com.sanjanaroyvaradarajula.moviebookingapp.moviesModule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanjanaroyvaradarajula.moviebookingapp.CheckInActivity
import com.sanjanaroyvaradarajula.moviebookingapp.R
import com.sanjanaroyvaradarajula.moviebookingapp.ui.theme.MovieBookingAppTheme


class FanProfileFragment : Fragment(R.layout.fragment_fan_profile) {

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.myProfile).setContent {
            MovieBookingAppTheme {
                FanProfile()
            }
        }
    }

}

@Composable
fun FanProfile() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.black))
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 6.dp),
            text = "My Profile",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.first))
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(painter = painterResource(id = R.drawable.user), contentDescription = "Profile")

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 6.dp),
                text = MovieFanData.fetchUserName(context),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 6.dp),
                text = MovieFanData.fetchUserMail(context),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .background(
                        color = colorResource(id = R.color.first),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.second),
                        shape = RoundedCornerShape(6.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.second))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "Contact Us",
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.third),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    text = "If you have experienced problems making a ticket booking, you can contact us via the mail.",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    text = "For any Insider queries please write email on varadarajula.sanjana193@gmail.com.",
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Center
                )

            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        MovieFanData.persistLoginState(context, false)
                        val intent = Intent(context, CheckInActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                        (context as Activity).finish()
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    text = "Logout",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
        }
    }
}