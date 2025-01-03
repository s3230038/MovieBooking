package com.sanjanaroyvaradarajula.moviebookingapp.moviesModule

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase
import com.sanjanaroyvaradarajula.moviebookingapp.R
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ShowDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowDetailsScreen()
        }
    }
}

@Composable
fun ShowDetailsScreen() {
    val context = LocalContext.current

    val selectedDate = remember { mutableStateOf("") }

    val selectedTime = remember { mutableStateOf("") }

    val next6Days = getNextSixDays()

    val showTimes = getShowTimes()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.black))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {

            Image(
                modifier = Modifier.clickable {
                    (context as Activity).finish()
                },
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back Arrow"
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${SelMovieDetail.movieName} ShowTimes",
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.first))
                .padding(horizontal = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier,
                text = "Select Date",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )


            Spacer(modifier = Modifier.height(12.dp))

            DisplayItemsInGrid(items = next6Days, selectedDate)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier,
                text = "Select Time",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            ShowTimeList(items = showTimes, selectedDate.value, selectedTime)

            BookingDetails(showDate = selectedDate.value, showTime = selectedTime.value)

        }
    }
}



@Composable
fun BookingDetails(showDate: String, showTime: String) {
    val context = LocalContext.current

    val tickets = remember { mutableIntStateOf(1) }

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
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.second))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Booking Details",
                fontSize = 20.sp,
                color = colorResource(id = R.color.third),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp),
                painter = painterResource(id = SelMovieDetail.movieThumbnail),
                contentDescription = "Thumbnail"
            )

            Column(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier,
                    text = SelMovieDetail.movieName,
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
                        text = SelMovieDetail.location,
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
                        text = "$showDate | $showTime",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        modifier = Modifier.clickable {
                            if (tickets.intValue > 1) {
                                tickets.intValue--
                            }
                        },
                        painter = painterResource(id = R.drawable.baseline_indeterminate_check_box_24),
                        contentDescription = ""
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                        text = "${tickets.intValue}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                    )

                    Image(
                        modifier = Modifier.clickable {
                            if (tickets.intValue < 10) {
                                tickets.intValue++
                            }
                        },
                        painter = painterResource(id = R.drawable.baseline_add_box_24),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.weight(1f))


                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier
                        .width(200.dp)
                        .background(
                            color = colorResource(id = R.color.second),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.first),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
//                        context.startActivity(Intent(context, BookDetailsActivity::class.java))
                            if (showDate.isEmpty()) {
                                Toast
                                    .makeText(context, "Select Date", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (showTime.isEmpty()) {
                                Toast
                                    .makeText(context, "Select Time", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                val movieBookingData = MovieBookingData(
                                    MovieFanData.fetchUserMail(context),
                                    SelMovieDetail.movieName,
                                    SelMovieDetail.location,
                                    showDate,
                                    showTime,
                                    tickets.intValue)

                                bookTicketToDB(movieBookingData, context)
                            }
                        },
                    text = "Book Now",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.third),
                )

            }
        }
    }
}

private fun bookTicketToDB(movieBookingData: MovieBookingData, context: Context) {

    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("Bookings")

    val bookingDate = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
    val bookingId = bookingDate.format(Date())

    databaseReference.child(movieBookingData.userMail.replace(".", ",")).child(bookingId).setValue(movieBookingData)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Tickets Booked Successfully", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, " Please bring your ID card during visit.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    context,
                    "Booking Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener { _ ->
            Toast.makeText(
                context,
                "Booking Failed",
                Toast.LENGTH_SHORT
            ).show()
        }
}


@Composable
fun DisplayItemsInGrid(items: List<String>, selectedDate: MutableState<String>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        items(items.size) { item ->

            ShowDateItem(
                modifier = Modifier,
                date = items[item],
                isSelected = selectedDate.value == items[item],
                onClick = { selectedDate.value = items[item] }
            )

        }
    }
}

@Composable
fun ShowDateItem(
    modifier: Modifier,
    date: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = if (isSelected) colorResource(id = R.color.second) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) colorResource(id = R.color.second) else Color.White,
                shape = RoundedCornerShape(6.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .clickable { onClick() },
            text = date,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = if (isSelected) colorResource(id = R.color.third) else Color.White,
        )
    }
}


@Composable
fun ShowTimeList(items: List<String>, selectedDate: String, selectedTime: MutableState<String>) {

//    val selectedTime = remember { mutableStateOf("") }


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp), // Space between rows
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between columns
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(items.size) { item ->

            ShowTimeItem(
                modifier = Modifier,
                time = items[item],
                selectedDate,
                isSelected = selectedTime.value == items[item],
                onClick = { selectedTime.value = items[item] }
            )

        }
    }
}


@Composable
fun ShowTimeItem(
    modifier: Modifier,
    time: String,
    date: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val isPast = isTimeBeforeNow(time)

    if (date == getNextSixDays()[0]) {
        Column(
            modifier = modifier
                .background(
                    color = if (isSelected && (!isPast)) colorResource(id = R.color.second) else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isSelected && (!isPast)) colorResource(id = R.color.second) else Color.White,
                    shape = RoundedCornerShape(6.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Log.e("Test", "SelDate , $date == ${getNextSixDays()[0]}")


            Text(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .clickable { if (!isPast) onClick() }, // Prevent clicking past times
                text = time,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = if (isSelected && (!isPast)) colorResource(id = R.color.third) else Color.White,
                textDecoration = if (isPast) TextDecoration.LineThrough else null // Strike through past times

            )

        }
    } else {

        Column(
            modifier = modifier
                .background(
                    color = if (isSelected) colorResource(id = R.color.second) else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isSelected) colorResource(id = R.color.second) else Color.White,
                    shape = RoundedCornerShape(6.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Log.e("Test", "SelDate , $date == ${getNextSixDays()[0]}")

            Text(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .clickable { onClick() }, // Prevent clicking past times
                text = time,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = if (isSelected) colorResource(id = R.color.third) else Color.White
            )

        }


    }
}

fun isTimeBeforeNow(time: String): Boolean {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val showTime = LocalTime.parse(time, formatter)
    val currentTime = LocalTime.now()
    return showTime.isBefore(currentTime)
}


fun getNextSixDays(): List<String> {
    val dateFormat = SimpleDateFormat("EEE dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    val nextSixDays = mutableListOf<String>()

    for (i in 0 until 6) {
        nextSixDays.add(dateFormat.format(calendar.time))
        calendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    return nextSixDays
}

fun getShowTimes(): List<String> {
    return listOf(
        "11:30", "12:30", "14:10", "15:10", "16:05", "16:50", "18:00", "18:45", "19:25", "20:40"
    )
}

data class MovieBookingData(
    var userMail: String = "",
    var movieName: String = "",
    var location: String = "",
    var showDate: String = "",
    var showTime: String = "",
    var tickets : Int = 0
)

