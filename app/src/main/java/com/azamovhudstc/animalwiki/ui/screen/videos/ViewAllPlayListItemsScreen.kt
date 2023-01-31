package com.azamovhudstc.animalwiki.ui.screen.videos

import android.app.Dialog
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.WindowManager
import android.widget.SearchView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.remote.response.playListResponse.Item
import com.azamovhudstc.animalwiki.service.receiver.AirplaneReceiver
import com.azamovhudstc.animalwiki.ui.adapter.ViewAllPlayListAdapter
import com.azamovhudstc.animalwiki.utils.extensions.hasConnection
import com.azamovhudstc.animalwiki.viewmodels.ViewAllPlayListItemsScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.ViewAllPlayListItemsScreenViewModelImp
import com.leo.materialsearchview.MaterialSearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_view_all_play_lists.*
import kotlinx.android.synthetic.main.no_connection.view.*


@AndroidEntryPoint
class ViewAllPlayListItemsScreen : Fragment(R.layout.fragment_view_all_play_lists),
    ViewAllPlayListAdapter.ContactItemCallBack.ViewAllAdapterClicker {
    private val adapter by lazy { ViewAllPlayListAdapter(this) }
    private lateinit var playListId: String
        val planeReceiver= AirplaneReceiver.getInstance()
    private val materialSearchView by lazy { MaterialSearchView(requireContext()) }
    private lateinit var playListTitle: String
    private val list = ArrayList<Item>()
    private val viewModel: ViewAllPlayListItemsScreenViewModel by viewModels<ViewAllPlayListItemsScreenViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        playListId = arguments?.getString("playListId").toString()
        playListTitle = arguments?.getString("playListsTitle").toString()
        requireActivity().registerReceiver(
            planeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this) {
            findNavController().popBackStack()
        }
        viewModel.getAllPlayListItemsLiveData.observe(this) {
            list.addAll(it.items)
            adapter.submitList(it.items)
        }
        viewModel.progressLiveData.observe(this) {
            if (it) viewAllProgress.visibility = View.VISIBLE
            else viewAllProgress.visibility = View.INVISIBLE
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Optional Parameters
        materialSearchView.setBackButtonTint(R.color.color_main)
        materialSearchView.animationDuration = 500
        materialSearchView.searchHint = "Search"
        materialSearchView.setBackButtonTint(R.color.color_main)
        materialSearchView.backButtonDrawable =
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.ic_baseline_arrow_back_ios_24
            )
        var handler = Handler(Looper.getMainLooper())
        materialSearchView.clearSearchOnDismiss = false
        materialSearchView.showKeyBoardDefault = false
        materialSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val query = newText.toString().lowercase()
                if (query.isEmpty()) {
                    adapter.submitList(list)
                }
                handler.postDelayed({
                    query?.let {
                        filter(query)
                    }
                }, 1000)
                return true
            }
        })


        searchViewByVideos.setOnClickListener {
            materialSearchView.show(it)
        }
        backViewAllPlayList.setOnClickListener {
            viewModel.backLiveData()
        }


        requireContext().registerReceiver(planeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        var dialog = Dialog(requireContext())

            if (hasConnection()){

                dialog.dismiss()
                rv_viewAll.visibility=View.VISIBLE
                viewModel.getAllViewAllPlayListItems(playListId)
                rv_viewAll.adapter = adapter
                app_bar_playList.text = playListTitle
            }
            else{
                viewAllProgress.visibility = View.INVISIBLE
                rv_viewAll.visibility=View.INVISIBLE
                val inflater = LayoutInflater.from(requireContext())
                var dialogView = inflater.inflate(R.layout.no_connection, null)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCancelable(false)

                dialogView.tryagain.setOnClickListener {
                    if (hasConnection()) {
                        dialog.dismiss()
                        viewModel.getAllViewAllPlayListItems(playListId)
                        rv_viewAll.adapter = adapter
                        app_bar_playList.text = playListTitle
                        rv_viewAll.visibility=View.VISIBLE
                    } else {
                        rv_viewAll.visibility=View.INVISIBLE
                        dialog.setContentView(dialogView)

                    }
                }
                dialog.setContentView(dialogView)

                dialog.show()

            }



    }

    private fun filter(text: String) {
        if (text.isNotEmpty()) {

            // creating a new array list to filter our data.
            val filteredlist = ArrayList<Item>()

            // running a for loop to compare elements.
            for (item in list) {
                // checking if the entered string matched with any item of our recycler view.
                if (
                    item.snippet.title.toString().toLowerCase()
                        .contains(text.lowercase())
                ) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredlist.add(item)
                }
            }
            if (filteredlist.isEmpty()) {
                // if no item is added in filtered list we are


                // displaying a toast message as no data found.
            } else {

                // at last we are passing that filtered
                // list to our adapter class.
                adapter.submitList(filteredlist)

            }
        }
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
        list.clear()
        materialSearchView.dismiss()

    }
}