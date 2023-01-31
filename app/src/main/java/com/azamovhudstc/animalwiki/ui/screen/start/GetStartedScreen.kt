package com.azamovhudstc.animalwiki.ui.screen.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.sharedpref.AppReference
import com.azamovhudstc.animalwiki.utils.enums.ScreenEnum
import com.azamovhudstc.animalwiki.viewmodels.GetStartedScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.SplashScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.GetStartedScreenViewModelImp
import com.azamovhudstc.animalwiki.viewmodels.imp.SplashScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.get_started_screen.*
@AndroidEntryPoint
class GetStartedScreen : Fragment(R.layout.get_started_screen) {
    private val viewModel: GetStartedScreenViewModel by viewModels<GetStartedScreenViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openHomeScreenLiveData.observe(this, openHomeObserver)

    }

    private val openHomeObserver = Observer<Unit> {
        val sharedPreferences = AppReference(requireContext())
        sharedPreferences.setToken("AAAAAAAAAA")
        findNavController().navigate(
            R.id.homeScreen,
            null,
            NavOptions.Builder().setPopUpTo(R.id.getStartedScreen, true).build()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = AppReference(requireContext())
        getStart.setOnClickListener {
            pref.currentScreen = ScreenEnum.HOME
            viewModel.openHomeScreen()
        }

    }
}