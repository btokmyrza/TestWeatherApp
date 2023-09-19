package kz.btokmyrza.weatherapp.presentation

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kz.btokmyrza.weatherapp.presentation.feature.city.search.CitySearchScreen
import kz.btokmyrza.weatherapp.presentation.feature.location.LocationService
import kz.btokmyrza.weatherapp.presentation.feature.weather.WeatherScreen
import kz.btokmyrza.weatherapp.presentation.feature.welcome.WelcomeScreen
import kz.btokmyrza.weatherapp.presentation.model.CityDvo
import kz.btokmyrza.weatherapp.presentation.navigation.NavArgs
import kz.btokmyrza.weatherapp.presentation.navigation.Route
import kz.btokmyrza.weatherapp.presentation.navigation.screen
import kz.btokmyrza.weatherapp.presentation.ui.theme.WeatherAppTheme

internal class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions()
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                ) { paddingValues ->
                    NavHost(
                        modifier = Modifier.padding(paddingValues),
                        navController = navController,
                        startDestination = Route.WELCOME_SCREEN,
                    ) {
                        screen(
                            route = Route.WELCOME_SCREEN,
                            direction = AnimatedContentTransitionScope.SlideDirection.Down,
                        ) {
                            val city = it.savedStateHandle.get<CityDvo>(NavArgs.CITY)
                            WelcomeScreen(
                                city = city,
                                scaffoldState = scaffoldState,
                                onFindCityClick = {
                                    navController.navigate(Route.CITY_SEARCH_SCREEN)
                                },
                                onNextClick = { navController.navigate(Route.WEATHER_SCREEN) },
                            )
                        }
                        screen(
                            route = Route.CITY_SEARCH_SCREEN,
                            direction = AnimatedContentTransitionScope.SlideDirection.Up,
                        ) {
                            CitySearchScreen(
                                navController = navController,
                                onNavigateUp = { navController.navigateUp() },
                            )
                        }
                        screen(route = Route.WEATHER_SCREEN) {
                            WeatherScreen()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationService()
    }

    private fun requestPermissions() {
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            startLocationService()
            // viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
        )
    }

    private fun startLocationService() {
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }

    private fun stopLocationService() {
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_STOP
            startService(this)
        }
    }
}