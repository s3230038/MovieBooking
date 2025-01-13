package movie.bookingappbysanjanaroyvaradarajula.s3230038

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import movie.bookingappbysanjanaroyvaradarajula.s3230038.moviesModule.CinemasFragment
import movie.bookingappbysanjanaroyvaradarajula.s3230038.moviesModule.FanProfileFragment
import movie.bookingappbysanjanaroyvaradarajula.s3230038.moviesModule.MyBookingsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var btMovies: LinearLayout
    private lateinit var btMyBookings: LinearLayout
    private lateinit var btUserProfile: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
    }

    private fun initViews() {
        btMovies = findViewById(R.id.btCinemas)
        btMyBookings = findViewById(R.id.btMyBookings)
        btUserProfile = findViewById(R.id.btProfile)

        supportFragmentManager.beginTransaction()
            .replace(R.id.movieFragmentsContainer, CinemasFragment())
            .addToBackStack(null).commit()

        btMovies.setOnClickListener {
            switchFragments(1)
        }

        btMyBookings.setOnClickListener {
            switchFragments(2)
        }
        btUserProfile.setOnClickListener {
            switchFragments(3)
        }
    }

    private fun switchFragments(fragment: Int) {
        when (fragment) {
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.movieFragmentsContainer, CinemasFragment())
                    .addToBackStack(null).commit()
            }

            2 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.movieFragmentsContainer, MyBookingsFragment())
                    .addToBackStack(null).commit()
            }

            3 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.movieFragmentsContainer, FanProfileFragment())
                    .addToBackStack(null).commit()
            }
        }

    }
}