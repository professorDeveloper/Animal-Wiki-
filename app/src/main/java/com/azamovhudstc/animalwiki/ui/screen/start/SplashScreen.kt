package com.azamovhudstc.animalwiki.ui.screen.start

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.sharedpref.AppReference
import com.azamovhudstc.animalwiki.viewmodels.SplashScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.SplashScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen() : Fragment(R.layout.splash_screen) {
    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.openGetStartedScreenLiveData.observe(this, openGetStartedObserver)
//        viewModel.openHomeScreenLiveData.observe(this, openHomeObserver)
    }
//
//    private val openHomeObserver = Observer<Unit> {
//        findNavController().navigate(
//            R.id.homeScreen,
//            null,
//            NavOptions.Builder().setPopUpTo(R.id.splashScreen, true).build()
//        )
//    }
//    private val openGetStartedObserver = Observer<Unit> {
//        findNavController().navigate(
//            R.id.getStartedScreen,
//            null,
//            NavOptions.Builder().setPopUpTo(R.id.splashScreen, true).build()
//        )
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = AppReference(requireContext())

    }

}