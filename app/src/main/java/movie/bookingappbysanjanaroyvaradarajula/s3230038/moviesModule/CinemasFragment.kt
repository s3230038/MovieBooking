package movie.bookingappbysanjanaroyvaradarajula.s3230038.moviesModule

import android.content.Intent
import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import movie.bookingappbysanjanaroyvaradarajula.s3230038.ui.theme.MovieBookingAppTheme
import kotlinx.coroutines.delay
import movie.bookingappbysanjanaroyvaradarajula.s3230038.R


class CinemasFragment : Fragment(R.layout.fragment_cinemas) {

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.cinemasFragment).setContent {
            MovieBookingAppTheme {
                CinemasFragmentDesign()
            }
        }
    }

}

@Composable
fun CinemasFragmentDesign() {
    val context = LocalContext.current

    val images = listOf(
        R.drawable.sonic_m1,
        R.drawable.mufasa_m2,
        R.drawable.betterman_m3,
        R.drawable.moana_m4,
        R.drawable.kraven_m5,
        R.drawable.wicked_m6,
    )


    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.black))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Cinemas",
                fontSize = 22.sp,
                color = Color.White, // Ensure text is visible against black
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }



        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.showcase_thumbnail),
            contentDescription = "ShowCase Cinemas",
            contentScale = ContentScale.FillBounds
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.first))
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState())

        ) {
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Showcase Cinema Teesside",
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Now Showing",
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            AutoImageSliderWithAnimation(images = images, intervalMillis = 3000L)

            Spacer(modifier = Modifier.height(24.dp))


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
                        context.startActivity(Intent(context, BookTicketsActivity::class.java))
                    },
                text = "Book Movie Tickets",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.third),
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "ABOUT US",
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Showcase and Showcase Cinema de Lux are committed to providing you with the ultimate film-going experience.\n" +
                        "\n" +
                        "Showcase and Showcase Cinema de Lux are owned and operated by National Amusements, Inc., a world leader in the motion picture exhibition industry operating 816 screens in the U.S., U.K., Argentina and Brazil. National Amusements delivers a superior entertainment experience in cinemas around the world under its Showcase, Multiplex, Showcase Cinema de Lux and UCI brands. Based in Massachusetts, National Amusements is a closely held company operating under the third generation of leadership by the Redstone family. National Amusements is the parent company of ViacomCBS.\n" +
                        "\n" +
                        "Customer Services can be contacted by completing the form found here. We are interested in your comments and read every e-mail. We consider all comments to review and improve our levels of service and facilities. We do receive a high volume of e-mails and so cannot guarantee an individual response to all communications. Please visit our Help Centre before contacting us to see if the answer you need is there.",
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify
            )

//            Text(
//                modifier = Modifier
//                    .width(200.dp)
//                    .background(
//                        color = colorResource(id = R.color.second),
//                        shape = RoundedCornerShape(8.dp)
//                    )
//                    .border(
//                        width = 1.dp,
//                        color = colorResource(id = R.color.first),
//                        shape = RoundedCornerShape(6.dp)
//                    )
//                    .padding(horizontal = 8.dp, vertical = 6.dp)
//                    .align(Alignment.CenterHorizontally)
//                    .clickable {
////                        context.startActivity(Intent(context, BookDetailsActivity::class.java))
//                    },
//                text = "About Us",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center,
//                color = colorResource(id = R.color.third),
//            )
        }
    }
}

@Composable
fun AutoImageSliderWithAnimation(images: List<Int>, intervalMillis: Long = 3000L) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    // Automatically change the image index
    LaunchedEffect(currentIndex) {
        while (true) {
            delay(intervalMillis)
            currentIndex = (currentIndex + 1) % images.size
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        images.forEachIndexed { index, imageRes ->

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = index == currentIndex,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(200.dp)
                        .height(250.dp)
                        .align(Alignment.Center)
                )
            }
        }

        // Dots Indicator
//        Row(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(8.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            images.forEachIndexed { index, _ ->
//                Box(
//                    modifier = Modifier
//                        .padding(4.dp)
//                        .size(8.dp)
//                        .clip(CircleShape)
//                        .background(if (index == currentIndex) Color.Black else Color.Gray)
//                )
//            }
//        }
    }
}

