package com.sanjanaroyvaradarajula.moviebookingapp.moviesModule

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sanjanaroyvaradarajula.moviebookingapp.R
import com.sanjanaroyvaradarajula.moviebookingapp.ui.theme.MovieBookingAppTheme


class MyBookingsFragment : Fragment(R.layout.fragment_my_bookings) {

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.myBookings).setContent {
            MovieBookingAppTheme {
                MyBookings()
            }
        }
    }

}

@Composable
fun MyBookings() {

    val context = LocalContext.current

    val fanMail = MovieFanData.fetchUserMail(context)

    var myBookingList by remember { mutableStateOf(listOf<MovieBookingData>()) }

    LaunchedEffect(fanMail) {
        fetchMyBookings(fanMail) { bookings ->
            myBookingList = bookings
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.black))
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 6.dp),
            text = "My Bookings",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.first))
                .padding(12.dp)

        ) {

            Spacer(modifier = Modifier.height(16.dp))

            if (myBookingList.isEmpty()) {
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    text = "Seems You didn't have any bookings",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.weight(1f))


            } else
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 12.dp)
                ) {

                    items(myBookingList.size) { index ->
                        MovieItem(
                            myBookingList[index]
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
        }
    }


}

@Composable
fun MovieItem(movieBookingData: MovieBookingData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.first),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.second),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                modifier = Modifier,
                text = movieBookingData.movieName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    modifier = Modifier,
                    text = movieBookingData.location,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(6.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    text = "${movieBookingData.showDate} | ${movieBookingData.showTime}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.tickets),
                contentDescription = ""
            )

            Text(
                modifier = Modifier,
                text = movieBookingData.tickets.toString(),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }


    }
}


fun fetchMyBookings(fanMail: String, callback: (List<MovieBookingData>) -> Unit) {
    val fanKey = fanMail.replace(".", ",")

    val databaseReference = FirebaseDatabase.getInstance().getReference("Bookings/$fanKey")

    databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val bookingsList = mutableListOf<MovieBookingData>()
            for (donationSnapshot in snapshot.children) {
                val booking = donationSnapshot.getValue(MovieBookingData::class.java)
                booking?.let { bookingsList.add(it) }
            }
            callback(bookingsList)
        }

        override fun onCancelled(error: DatabaseError) {
            callback(emptyList())
        }
    })
}