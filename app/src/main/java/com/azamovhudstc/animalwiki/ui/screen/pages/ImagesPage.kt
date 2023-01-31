package com.azamovhudstc.animalwiki.ui.screen.pages

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.model.ImageInfo
import com.azamovhudstc.animalwiki.data.remote.response.imagesResponse.ImageItem
import com.azamovhudstc.animalwiki.service.receiver.AirplaneReceiver
import com.azamovhudstc.animalwiki.ui.adapter.ImagePagAdapter
import com.azamovhudstc.animalwiki.ui.adapter.ImagesAllAdapter
import com.azamovhudstc.animalwiki.utils.consts.Filter
import com.azamovhudstc.animalwiki.utils.extensions.showSnack
import com.azamovhudstc.animalwiki.viewmodels.imp.ImagePageViewModelImp
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_filter.view.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.save_settings_button
import kotlinx.android.synthetic.main.fragment_image_info_screen.*
import kotlinx.android.synthetic.main.fragment_images_page.*
import kotlinx.android.synthetic.main.fragment_post_item_info_screen.*
import kotlinx.android.synthetic.main.fragment_videos_page.*
import kotlinx.android.synthetic.main.item_bottom_sheet_info_dialog.view.*
import kotlinx.android.synthetic.main.no_connection.view.*

@AndroidEntryPoint
class ImagesPage : Fragment(R.layout.fragment_images_page),
    ImagesAllAdapter.ContactItemCallBack.SetLongClickListener {
    private val adapter by lazy { ImagePagAdapter(requireContext(), this) }
    private val query = "animals"

    private val planeReceiver by lazy { AirplaneReceiver.getInstance() }

    private val viewModel by viewModels<ImagePageViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)

        requireActivity().registerReceiver(
            planeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        viewModel.photos.observe(this) {
            progressImages.visibility = View.VISIBLE
            adapter.submitData(lifecycle = this.lifecycle, it)
            progressImages.visibility = View.INVISIBLE
        }

    }



    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_home_image.setHasFixedSize(true)
        var dialog = Dialog(requireContext())
        rv_home_image.adapter = adapter
        rv_home_image.visibility=View.VISIBLE


        planeReceiver.setListener { state->
            if (state) {
                viewModel.searchPhoto(query)
                rv_home_image.adapter = adapter
            }
            else{
                val inflater = LayoutInflater.from(requireContext())
                var dialogView = inflater.inflate(R.layout.no_connection, null)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCancelable(true)
                dialogView.tryagain.setOnClickListener {
                    if (state) {
                        rv_home_image.visibility=View.VISIBLE
                        viewModel.searchPhoto(query)
                        rv_home_image.adapter = adapter
                        dialog.dismiss()
                    } else {
                        showSnack("Internet o`chiq")
                        dialog.setContentView(dialogView)
                    }
                }
                dialog.setContentView(dialogView)

                dialog.show()

            }

        }

        filter.setOnClickListener {
            var str = ""
            var dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
            val inflater = LayoutInflater.from(requireContext())
            var view = inflater.inflate(R.layout.bottom_sheet_filter, null)
            if (Filter.filter1Position!=0){
                view.radioGrouper.check(Filter.filter1Position)
            }
            else{
                view.radioGrouper.clearCheck()
            }
            if (Filter.filter2Position!=0){
                view.radioGroup2.check(Filter.filter2Position)
            }
            else{
                view.radioGroup2.clearCheck()
            }
            if (Filter.filter3Position!=0){
                view.radioGroup3.check(Filter.filter3Position)
            }
            else{
                view.radioGroup3.clearCheck()
            }
            dialog.setContentView(view)
            view.clearFilter.setOnClickListener {
                view.radioGrouper.clearCheck()
                view.radioGroup2.clearCheck()
                view.radioGroup3.clearCheck()
            }
            view.save_settings_button.setOnClickListener {
                val getRadioIdByRadioGroup1 = view.radioGrouper.checkedRadioButtonId
                val getRadioIdByRadioGroup2 = view.radioGroup2.checkedRadioButtonId
                val getRadioIdByRadioGroup3 = view.radioGroup3.checkedRadioButtonId
                if (getRadioIdByRadioGroup1 != -1) {
                    Filter.filter1Position=getRadioIdByRadioGroup1
                    var radioButton1 = view.findViewById<RadioButton>(getRadioIdByRadioGroup1)
                    str = radioButton1.text.toString()
                    dialog.dismiss()

                }
                else{
                    Filter.filter1Position=0
                    dialog.dismiss()
                }
                if (getRadioIdByRadioGroup2 != -1) {
                    Filter.filter2Position=getRadioIdByRadioGroup2
                    var radioButton1 = view.findViewById<RadioButton>(getRadioIdByRadioGroup2)
                    str = "$str ${radioButton1.text}"


                }
                else{
                    Filter.filter2Position=0
                    dialog.dismiss()
                }
                if (getRadioIdByRadioGroup3 != -1) {
                    Filter.filter3Position=getRadioIdByRadioGroup3
                    var radioButton1 = view.findViewById<RadioButton>(getRadioIdByRadioGroup3)
                    str = "$str ${radioButton1.text}"


                }
                else{
                    Filter.filter3Position=0
                }
                if (str!=""){
                    viewModel.searchPhoto(str)
                }

                dialog.dismiss()
            }

            dialog.create()
            dialog.show()


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireContext().unregisterReceiver(planeReceiver)
        println("onDestroyView")
    }

    override fun onClick(item: ImageItem) {
        var bundle = Bundle()
        var imageInfo = ImageInfo(
            width = item.width.toString(),
            date = item.createdAt,
            height = item.height.toString(),
            likes = item.likes.toString(),
            authorName = item.user.name,
            authorWebSite = item.user.links.html.toString(),
            imageUrl = item.urls.regular
        )
        bundle.putSerializable("itemImage", imageInfo)
        findNavController().navigate(R.id.imageInfoScreen, bundle)
    }
}