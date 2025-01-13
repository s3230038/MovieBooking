package movie.bookingappbysanjanaroyvaradarajula.s3230038.moviesModule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng
import movie.bookingappbysanjanaroyvaradarajula.s3230038.R

class BookTicketsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookTicketsDesign()
        }
    }
}

object SelMovieDetail {
    var movieId: Int = 0
    var movieName: String = "THE MOVIE"
    var movieThumbnail: Int = 0
    var location: String = "Location"
    var showDate: String = ""
    var showTime: String = ""
    var cinemasLocation = LatLng(51.4389776,0.2684943)
}

@Composable
fun BookTicketsDesign() {
    val context = LocalContext.current

    val selectedCity = remember { mutableStateOf("Bluewater") }


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
                text = "Book Tickets",
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
                modifier = Modifier.fillMaxWidth(),
                text = "Select Location",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {

                CityText(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    cityName = "Bluewater",
                    isSelected = selectedCity.value == "Bluewater",
                    onClick = { selectedCity.value = "Bluewater" }
                )
                Spacer(modifier = Modifier.width(4.dp))
                CityText(
                    modifier = Modifier.weight(1f),
                    cityName = "Cardiff Nantgarw",
                    isSelected = selectedCity.value == "Cardiff Nantgarw",
                    onClick = { selectedCity.value = "Cardiff Nantgarw" }
                )
                Spacer(modifier = Modifier.width(4.dp))
                CityText(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    cityName = "Derby",
                    isSelected = selectedCity.value == "Derby",
                    onClick = { selectedCity.value = "Derby" }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SelMovieDetail.location = selectedCity.value

                if (selectedCity.value == "Bluewater") {
                    MovieItem(movieName = "Sonic the hedgehog 3", thumbnail = R.drawable.sonic_m1)
                    MovieItem(movieName = "Mufasa", thumbnail = R.drawable.mufasa_m2)
                } else if (selectedCity.value == "Cardiff Nantgarw") {
                    MovieItem(movieName = "Betterman", thumbnail = R.drawable.betterman_m3)
                    MovieItem(movieName = "Moana 2", thumbnail = R.drawable.moana_m4)
                } else {
                    MovieItem(movieName = "Wicked", thumbnail = R.drawable.wicked_m6)
                    MovieItem(movieName = "Kraven the hunter", thumbnail = R.drawable.kraven_m5)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .background(
                        color = colorResource(id = R.color.second),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.first),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clickable {
                        if(selectedCity.value == "Bluewater")
                        {
                            SelMovieDetail.cinemasLocation = LatLng(51.4389776,0.2684943)
                        }else if(selectedCity.value == "Cardiff Nantgarw")
                        {
                            SelMovieDetail.cinemasLocation = LatLng(51.565397,-3.2767906)
                        }else{
                            SelMovieDetail.cinemasLocation = LatLng(52.9146611,-1.5523688)
                        }

                        context.startActivity(Intent(context, CinemasLocationActivity::class.java))
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier,
                    text = "Get Directions",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.third),
                )

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.directions),
                    contentDescription = "Directions"
                )

                Spacer(modifier = Modifier.weight(1f))

            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun CityText(
    modifier: Modifier,
    cityName: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .background(
                color = if (isSelected) colorResource(id = R.color.white) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = cityName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = if (isSelected) colorResource(id = R.color.third) else Color.White,
        )
    }
}

@Composable
fun MovieItem(movieName: String, thumbnail: Int) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .width(150.dp)
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
        Image(
            modifier = Modifier
                .width(150.dp)
                .height(200.dp),
            painter = painterResource(id = thumbnail),
            contentDescription = "Movie",
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.second)
                )
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    SelMovieDetail.movieName = movieName
                    SelMovieDetail.movieThumbnail = thumbnail
                    context.startActivity(Intent(context, ShowDetailsActivity::class.java))
                },
            text = "Book Tickets",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.third),
        )
    }
}