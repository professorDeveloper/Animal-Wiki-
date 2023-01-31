package com.azamovhudstc.animalwiki.ui.screen.videos

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.remote.response.videoInfoResponse.VideoInfoResponse
import com.azamovhudstc.animalwiki.service.receiver.AirplaneReceiver
import com.azamovhudstc.animalwiki.ui.adapter.CommentsAdapter
import com.azamovhudstc.animalwiki.utils.extensions.hasConnection
import com.azamovhudstc.animalwiki.utils.extensions.showSnack
import com.azamovhudstc.animalwiki.viewmodels.VideInfoScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.VideInfoScreenViewModelImp
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_videos_about_screen.*
import kotlinx.android.synthetic.main.no_connection.view.*


@AndroidEntryPoint
class VideosAboutScreen : Fragment(R.layout.fragment_videos_about_screen) {
    private val viewModel: VideInfoScreenViewModel by viewModels<VideInfoScreenViewModelImp>()
    private val adapter by lazy { CommentsAdapter() }
    private lateinit var videoId: String
    private lateinit var youTubePlayerView: YouTubePlayerView
    private val planeReceiver by lazy { AirplaneReceiver.getInstance() }
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
        viewModel.videoInfoLiveData.observe(this, videoInformationObserver)
        viewModel.progressLiveData.observe(this, progressObserver)
    }


    private val progressObserver = Observer<Boolean> {

        if (it) {
            containerVideoInfo.visibility = View.INVISIBLE
            progressVideoInfoScreen.visibility = View.VISIBLE
        } else {
            progressVideoInfoScreen.visibility = View.INVISIBLE
            containerVideoInfo.visibility = View.VISIBLE
        }
    }
    private val videoInformationObserver = Observer<VideoInfoResponse> { _videoInfo: VideoInfoResponse? ->
            var videoInfo = _videoInfo!!.items[0]
            titleVideoInfo.text = videoInfo.snippet.title
            if (_videoInfo.items[0].statistics.commentCount!=null){
                commentsTitle.text = _videoInfo.items[0].statistics.commentCount

            }
            else{
                commentsTitle.text = "0"
            }
            descriptionVideInfo.text = videoInfo.snippet.description
            showsVideoInfo.text = videoInfo.statistics.viewCount
            likesVideoInfo.text = videoInfo.statistics.likeCount

        }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dialog = Dialog(requireContext())
        var channelId = arguments?.getString("channelId")!!
        videoId = arguments?.getString("videoId")!!
        youTubePlayerView = view.findViewById(R.id.youtubeVideo)
        lifecycle.addObserver(youTubePlayerView)
        initUi()
        viewModel.getComments(videoId)
        youTubePlayerView.enterFullScreen()
        youTubePlayerView.toggleFullScreen()
        fullScreen.setOnClickListener {
            youTubePlayerView.enterFullScreen()
            youTubePlayerView.toggleFullScreen()
            println("if")
            youTubePlayerView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            fullScreen.visibility = View.GONE
            fullExitScreen.visibility = View.VISIBLE

        }
        fullExitScreen.setOnClickListener {

            youTubePlayerView.exitFullScreen()
            youTubePlayerView.toggleFullScreen()
            youTubePlayerView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            fullScreen.visibility = View.VISIBLE
            fullExitScreen.visibility = View.GONE

        }
        if (hasConnection()) {
            dialog.dismiss()
            viewModel.getFullInformation(channelId, videoId)
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        } else {
            val inflater = LayoutInflater.from(requireContext())
            var dialogView = inflater.inflate(R.layout.no_connection, null)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialogView.tryagain.setOnClickListener {
                if (hasConnection()) {
                    viewModel.getFullInformation(channelId, videoId)
                    youTubePlayerView.addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })
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

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    private fun initUi() {
        backVideosScreen.setOnClickListener {
            findNavController().popBackStack()
        }
        share.setOnClickListener {
            val intentt = Intent(Intent.ACTION_SEND)

            intentt.type = "text/plain"
            intentt.putExtra(Intent.EXTRA_SUBJECT, "https://youtube.com/$videoId")

            intentt.putExtra(Intent.EXTRA_TEXT, "https://youtube.com/$videoId")
            startActivity(Intent.createChooser(intentt, "Share Via"))

        }
    }
}
