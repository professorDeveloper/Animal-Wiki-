package com.azamovhudstc.animalwiki.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.viewmodels.MainViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.MainViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var host: NavHostFragment
    private lateinit var graph: NavGraph
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    override fun onCreate(savedInstanceState: Bundle?) {
            installSplashScreen()
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
         host = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
         graph = host.navController.navInflater.inflate(R.navigation.app_nav_graph)

        viewModel.homeScreenLiveData.observe(this, openHomeScreenObserver)
        viewModel.loginScreenLiveData.observe(this, getStartedScreenObserver)

    }
    private val openHomeScreenObserver=Observer<Unit>{
        graph.setStartDestination(R.id.homeScreen)
        host.navController.graph = graph

    }
    private val getStartedScreenObserver=Observer<Unit>{
        graph.setStartDestination(R.id.getStartedScreen)
        host.navController.graph = graph
    }
}