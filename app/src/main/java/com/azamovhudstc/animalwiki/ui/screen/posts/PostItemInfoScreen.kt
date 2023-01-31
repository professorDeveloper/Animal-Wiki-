package com.azamovhudstc.animalwiki.ui.screen.posts

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.azamovhudstc.animalwiki.viewmodels.PostItemInfoScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.PostItemInfoScreenImp
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*
import kotlinx.android.synthetic.main.fragment_post_item_info_screen.*
import kotlinx.android.synthetic.main.fragment_posts_item_screen.*


class PostItemInfoScreen : Fragment(R.layout.fragment_post_item_info_screen) {
    var chek = 0
    private val viewModel: PostItemInfoScreenViewModel by viewModels<PostItemInfoScreenImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requireActivity().requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this) {
            findNavController().popBackStack()
        }
        viewModel.openShareScreenLiveData.observe(this) {
            shareTextOnly(postAboutDes.text.toString())
        }
        viewModel.showInfoLiveData.observe(this, showInfoObserver)
        viewModel.openTextSizeLiveData.observe(this, textSizeObserver)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private val textSizeObserver = Observer<Unit> {
        var dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        val inflater = LayoutInflater.from(requireContext())

        var view = inflater.inflate(R.layout.bottom_sheet_layout, null)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (chek != 0) view.radioGroup.check(chek)
        view.save_settings_button.setOnClickListener {
            val checkedRadioButtonId = view.radioGroup.checkedRadioButtonId
            chek = checkedRadioButtonId
            var radioButton: RadioButton = view.findViewById(checkedRadioButtonId)
            val toInt = radioButton.text.toString().substring(0, 2).toInt()
            postAboutDes.textSize = toInt.toFloat()
            dialog.dismiss()
        }
        println()
        dialog.setContentView(view)

        dialog.create()
        dialog.show()
    }
    private val showInfoObserver = Observer<WikiPostData> {
        postAboutDes.text = it.des
        Glide.with(requireContext()).load(it.imgData).into(postAboutImage)
        postAboutTitle.text = it.title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var infoo = arguments?.getSerializable("Keyer") as WikiPostData
        viewModel.showInfo(infoo)
        postAboutShare.setOnClickListener {
            viewModel.openShareScreen()
        }
        postAboutTextSize.setOnClickListener {
            viewModel.openTextSize()
        }
        backPostItems.setOnClickListener {
            viewModel.back()
        }

    }

    private fun shareTextOnly(title: String) {


        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")

        intent.putExtra(Intent.EXTRA_TEXT, title)
        startActivity(Intent.createChooser(intent, "Share "))
    }

}