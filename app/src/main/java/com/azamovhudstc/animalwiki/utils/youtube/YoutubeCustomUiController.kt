package com.azamovhudstc.animalwiki.utils.youtube

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.azamovhudstc.animalwiki.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

internal class YoutubeCustomUiController(
    private val context: Context,
    private val playerUi: View,
    private val youTubePlayer: YouTubePlayer,
    private val youTubePlayerView: YouTubePlayerView
) :
/** This CODE is WRITE Activity OR Fragment
 * val customPlayerUi = youTubePlayerView.inflateCustomPlayerUi(R.layout.custom_player_ui)
youTubePlayerView.enableAutomaticInitialization=fa
val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
override fun onReady(youTubePlayer: YouTubePlayer) {
val customPlayerUiController = YoutubeCustomUiController(requireContext(), customPlayerUi, youTubePlayer, youTubePlayerView)

youTubePlayer.addListener(customPlayerUiController)
youTubePlayerView.addFullScreenListener(customPlayerUiController)
}
}
// disable web ui
// disable web ui
val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()

youTubePlayerView.initialize(listener, options)
override fun onConfigurationChanged(newConfig: Configuration) {
super.onConfigurationChanged(newConfig)

// Checks the orientation of the screen
if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
youTubePlayerView.enterFullScreen()
} else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
youTubePlayerView.exitFullScreen()
}
}

 */
    AbstractYouTubePlayerListener(), YouTubePlayerFullScreenListener {
    // panel is used to intercept clicks on the WebView, I don't want the user to be able to click the WebView directly.
    private var panel: View? = null
    private var progressbar: View? = null
    private var videoCurrentTimeTextView: TextView? = null
    private var videoDurationTextView: TextView? = null
    private val playerTracker: YouTubePlayerTracker = YouTubePlayerTracker()
    private var fullscreen = false
    private fun initViews(playerUi: View) {
        panel = playerUi.findViewById(R.id.panel)
        progressbar = playerUi.findViewById(R.id.progressbar)
        videoCurrentTimeTextView = playerUi.findViewById(R.id.video_current_time)
        videoDurationTextView = playerUi.findViewById(R.id.video_duration)
        val playPauseButton = playerUi.findViewById<Button>(R.id.play_pause_button)
        val enterExitFullscreenButton =
            playerUi.findViewById<Button>(R.id.enter_exit_fullscreen_button)
        playPauseButton.setOnClickListener { view: View? -> if (playerTracker.state == PlayerState.PLAYING) youTubePlayer.pause() else youTubePlayer.play() }
        enterExitFullscreenButton.setOnClickListener { view: View? ->
            if (fullscreen) youTubePlayerView.exitFullScreen() else youTubePlayerView.enterFullScreen()
            fullscreen = !fullscreen
        }
    }

    override fun onReady(youTubePlayer: YouTubePlayer) {
        progressbar!!.visibility = View.GONE
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerState) {
        if (state == PlayerState.PLAYING || state == PlayerState.PAUSED || state == PlayerState.VIDEO_CUED) panel!!.setBackgroundColor(
            ContextCompat.getColor(
                context, android.R.color.transparent
            )
        ) else if (state == PlayerState.BUFFERING) panel!!.setBackgroundColor(
            ContextCompat.getColor(
                context, android.R.color.transparent
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        videoCurrentTimeTextView!!.text = second.toString() + ""
    }

    @SuppressLint("SetTextI18n")
    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
        videoDurationTextView!!.text = duration.toString() + ""
    }

    override fun onYouTubePlayerEnterFullScreen() {
        val viewParams = playerUi.layoutParams
        viewParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        viewParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        playerUi.layoutParams = viewParams
    }

    override fun onYouTubePlayerExitFullScreen() {
        val viewParams = playerUi.layoutParams
        viewParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        viewParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        playerUi.layoutParams = viewParams
    }

    init {
        youTubePlayer.addListener(playerTracker)
        initViews(playerUi)
    }
}
