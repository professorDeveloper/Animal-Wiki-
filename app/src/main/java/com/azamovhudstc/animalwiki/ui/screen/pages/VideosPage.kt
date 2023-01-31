package com.azamovhudstc.animalwiki.ui.screen.pages

import android.app.Dialog
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.sharedpref.AppReference
import com.azamovhudstc.animalwiki.data.model.GeneralModel
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.Item
import com.azamovhudstc.animalwiki.service.receiver.AirplaneReceiver
import com.azamovhudstc.animalwiki.ui.adapter.PlayListItemsAdapter
import com.azamovhudstc.animalwiki.ui.adapter.PlayListsAdapter
import com.azamovhudstc.animalwiki.utils.extensions.hasConnection
import com.azamovhudstc.animalwiki.utils.extensions.showSnack
import com.azamovhudstc.animalwiki.viewmodels.VideosScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.VideosScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_videos_page.*
import kotlinx.android.synthetic.main.no_connection.view.*

@AndroidEntryPoint
class VideosPage : Fragment(R.layout.fragment_videos_page),
    PlayListsAdapter.ContactItemCallBack.SetLongClickListener,
    PlayListItemsAdapter.ContactItemCallBack.PlayListsItemOnClick {
    private val adapter by lazy { PlayListsAdapter(this, this) }
    private val planeReceiver by lazy { AirplaneReceiver.getInstance() }
    private val viewModel: VideosScreenViewModel by viewModels<VideosScreenViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        requireActivity().registerReceiver(
            planeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        );
        viewModel.progressData.observe(this) {
            if (it) progressVideosScreen.visibility = View.VISIBLE
            else progressVideosScreen.visibility = View.INVISIBLE
        }
        viewModel.viewAllPlayList.observe(this) { generalModel ->
            var bundle = Bundle()
            bundle.putString("playListId", generalModel.quranResponse.items[0].snippet.playlistId)
            bundle.putString("playListsTitle", generalModel.title)
            findNavController().navigate(R.id.viewAllPlayListItemsScreen, bundle)
        }
        viewModel.quranPlayListsLiveData.observe(this) {
            adapter.submitList(it)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var shared = AppReference(requireContext())
        var dialog = Dialog(requireContext())
            if (hasConnection()) {
                dialog.dismiss()
                viewModel.getAllGeneralData()
                rv_playLists.adapter = adapter
                rv_playLists.visibility=View.VISIBLE
                if (shared.userName.toString().isEmpty()) {
                    showSnack("Iltimos Kuting....", Toast.LENGTH_LONG)
                    shared.userName = "Ajoyib Aktyor lekin "


                }
            }
            else{
                progressVideosScreen.visibility=View.INVISIBLE
                rv_playLists.visibility=View.INVISIBLE
                val inflater = LayoutInflater.from(requireContext())
                var dialogView = inflater.inflate(R.layout.no_connection, null)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCancelable(false)

                dialogView.tryagain.setOnClickListener {
                    if (hasConnection()) {
                        viewModel.getAllGeneralData()
                        rv_playLists.visibility=View.VISIBLE
                        rv_playLists.adapter = adapter
                        dialog.dismiss()
                    } else {
                        progressVideosScreen.visibility=View.INVISIBLE
                        rv_playLists.visibility=View.INVISIBLE
                        showSnack("Internet o`chiq")
                        dialog.setContentView(dialogView)
                    }
                }
                dialog.setContentView(dialogView)

                dialog.show()

            }



    }

    override fun onClick(generalModel: GeneralModel) {
        viewModel.viewAllPlayList(generalModel)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().unregisterReceiver(planeReceiver)
    }

    override fun onClick(item: Item) {
        var bundle = Bundle()
        var channelId = item.snippet.channelId
        var videId = item.contentDetails.videoId
        bundle.putString("channelId", channelId)
        bundle.putString("videoId", videId)
        findNavController().navigate(R.id.videosAboutScreen, bundle)

    }


}