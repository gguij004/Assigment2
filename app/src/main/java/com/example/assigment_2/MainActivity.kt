package com.example.assigment_2

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.assigment_2.databinding.ActivityMainBinding
import com.example.assigment_2.model.ItunesResponse
import com.example.assigment_2.model.remote.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        getItunesData("rock", "music", "song")

    }

        private fun getItunesData(itunesTerm: String, itunesMedia: String, itunesEntity: String) {
        Network().api.getNextItunesPage(
            itunesTerm, 0
        ).enqueue(
            object : Callback<ItunesResponse> {
                override fun onResponse(
                    call: Call<ItunesResponse>,
                    response: Response<ItunesResponse>
                ) {
                    //You have a bookResponse
                    //You have a Empty Response.
                    if (response.isSuccessful) {
                        println("estoy en success")
                        println(response.message())
                        response.body()?.let {
                            Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        println("estoy en success else ")
                        println(response.message())
                        //Log.d(TAG, "onResponse: ${response.message()}")
                    }

                }

                override fun onFailure(call: Call<ItunesResponse>, t: Throwable) {
                    println("estoy en failures")
                    println(t.message)
                   // Log.d(TAG, "onFailure: ${t.message}")
                    t.printStackTrace()
                }
            }
        )

    }
}