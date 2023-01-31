package com.azamovhudstc.animalwiki.ui.screen.image

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.model.ImageInfo
import com.azamovhudstc.animalwiki.service.receiver.AirplaneReceiver
import com.azamovhudstc.animalwiki.utils.extensions.hasConnection
import com.azamovhudstc.animalwiki.utils.extensions.showSnack
import com.azamovhudstc.animalwiki.viewmodels.ImageInfoScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.ImageInfoScreenViewModelImp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.fragment_image_info_screen.*
import kotlinx.android.synthetic.main.item_bottom_sheet_info_dialog.view.*
import kotlinx.android.synthetic.main.no_connection.view.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ImageInfoScreen : Fragment(R.layout.fragment_image_info_screen) {
    val planeReceiver = AirplaneReceiver.getInstance()
    private val viewModel: ImageInfoScreenViewModel by viewModels<ImageInfoScreenViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        viewModel.backLiveData.observe(this) {
            findNavController().popBackStack()
        }
        requireActivity().registerReceiver(
            planeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bitmap: Bitmap? = null
        var alert = Dialog(requireContext())

        var itemData = arguments?.getSerializable("itemImage") as ImageInfo
        planeReceiver.setListener { state ->
            if (state) {
                alert.dismiss()
                val circularProgressDrawable = CircularProgressDrawable(requireContext())
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()
                progressInfoImage.visibility = View.VISIBLE
                Glide.with(view).asBitmap().load(itemData.imageUrl)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                        ) {
                            progressInfoImage.visibility = View.INVISIBLE
                            home_image_view.setImageBitmap(resource)
                            bitmap = resource
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            // this is called when imageView is cleared on lifecycle call or for
                            // some other reason.
                            // if you are referencing the bitmap somewhere else too other than this imageView
                            // clear it here as you can no longer have the bitmap
                        }
                    })
            } else {
                val inflater = LayoutInflater.from(requireContext())
                var view = inflater.inflate(R.layout.no_connection, null)
                alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alert.setCancelable(false)
                view.tryagain.setOnClickListener {
                    if (hasConnection()) {
                        alert.dismiss()
                        val circularProgressDrawable = CircularProgressDrawable(requireContext())
                        circularProgressDrawable.strokeWidth = 5f
                        circularProgressDrawable.centerRadius = 30f
                        circularProgressDrawable.start()
                        progressInfoImage.visibility = View.VISIBLE
                        Glide.with(view).asBitmap().load(itemData.imageUrl)
                            .into(object : CustomTarget<Bitmap>() {
                                override fun onResourceReady(
                                    resource: Bitmap,
                                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                                ) {
                                    progressInfoImage.visibility = View.INVISIBLE
                                    home_image_view.setImageBitmap(resource)
                                    bitmap = resource
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {
                                    // this is called when imageView is cleared on lifecycle call or for
                                    // some other reason.
                                    // if you are referencing the bitmap somewhere else too other than this imageView
                                    // clear it here as you can no longer have the bitmap
                                }
                            })
                    } else {
                    }
                }
                alert.setContentView(view)
                alert.show()

            }
        }

        if (hasConnection()) {
            val circularProgressDrawable = CircularProgressDrawable(requireContext())
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            progressInfoImage.visibility = View.VISIBLE
            Glide.with(view).asBitmap().load(itemData.imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        progressInfoImage.visibility = View.INVISIBLE
                        home_image_view.setImageBitmap(resource)
                        bitmap = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })
        } else {
            var alert = Dialog(requireContext())
            val inflater = LayoutInflater.from(requireContext())
            var view = inflater.inflate(R.layout.no_connection, null)
            alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alert.setCancelable(false)
            view.tryagain.setOnClickListener {
                if (hasConnection()) {
                    alert.dismiss()
                    val circularProgressDrawable = CircularProgressDrawable(requireContext())
                    circularProgressDrawable.strokeWidth = 5f
                    circularProgressDrawable.centerRadius = 30f
                    circularProgressDrawable.start()
                    progressInfoImage.visibility = View.VISIBLE
                    Glide.with(view).asBitmap().load(itemData.imageUrl)
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                            ) {
                                progressInfoImage.visibility = View.INVISIBLE
                                home_image_view.setImageBitmap(resource)
                                bitmap = resource
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {
                                // this is called when imageView is cleared on lifecycle call or for
                                // some other reason.
                                // if you are referencing the bitmap somewhere else too other than this imageView
                                // clear it here as you can no longer have the bitmap
                            }
                        })
                } else {
                    alert.show()
                }
            }
            alert.setContentView(view)
            alert.show()
        }

        back_to_home_fragment1.setOnClickListener {
            viewModel.back()
        }
        home_image_info_btn.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
            val view = layoutInflater.inflate(R.layout.item_bottom_sheet_info_dialog, null, false)
            view.authorUrl.text = "Website: ${itemData.authorWebSite}"
            view.authorNameImage.text = "Author:  ${itemData.authorName}"
            view.downloadsImage.text = "Created:  ${itemData.date.toString()}"
            view.likesImage.text = "Likes:  ${itemData.likes.toInt()}"
            view.sizeImage.text = "size:  ${itemData.height} x ${itemData.width}  "
            dialog.setContentView(view)
            dialog.show()
            home_image_info_btn1.isClickable = false
            dialog.setOnDismissListener {
                home_image_info_btn1.isClickable = true
            }

        }
        setWallpaper.setOnClickListener {
            PermissionX.init(requireActivity())
                .permissions(Manifest.permission.SET_WALLPAPER)
                .onExplainRequestReason { scope, deniedList ->
                    scope.showRequestReasonDialog(
                        deniedList,
                        "Core fundamental are based on these permissions",
                        "OK",
                        "Cancel"
                    )
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        showSnack("Iltimos kuting ....")
                        val wallpaperManager = WallpaperManager.getInstance(requireContext())
                        wallpaperManager.setBitmap(bitmap!!)

                        Snackbar.make(requireView(), "Set Wallpaper", Toast.LENGTH_SHORT).show()


                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "These permissions are denied: $deniedList",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
        setWallpaper1.setOnClickListener {
            PermissionX.init(requireActivity())
                .permissions(Manifest.permission.SET_WALLPAPER)
                .onExplainRequestReason { scope, deniedList ->
                    scope.showRequestReasonDialog(
                        deniedList,
                        "Core fundamental are based on these permissions",
                        "OK",
                        "Cancel"
                    )
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        val wallpaperManager = WallpaperManager.getInstance(requireContext());
                        Glide.with(this)
                            .asBitmap()
                            .load(itemData.imageUrl)
                            .into(object : CustomTarget<Bitmap>() {
                                override fun onResourceReady(
                                    resource: Bitmap,
                                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                                ) {
                                    showSnack("Iltimos kuting ....")
                                    wallpaperManager.setBitmap(resource)
                                    Snackbar.make(
                                        requireView(),
                                        "Set Wallpaper",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {
                                    // this is called when imageView is cleared on lifecycle call or for
                                    // some other reason.
                                    // if you are referencing the bitmap somewhere else too other than this imageView
                                    // clear it here as you can no longer have the bitmap
                                }
                            })

                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "These permissions are denied: $deniedList",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
        home_image_info_btn1.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
            val view = layoutInflater.inflate(R.layout.item_bottom_sheet_info_dialog, null, false)
            view.authorUrl.text = "Website: ${itemData.authorWebSite}"
            view.authorNameImage.text = "Author:  ${itemData.authorName}"
            view.downloadsImage.text = "Created:  ${itemData.date.toString()}"
            view.likesImage.text = "Likes:  ${itemData.likes.toInt()}"
            view.sizeImage.text = "size:  ${itemData.height} x ${itemData.width}  "
            dialog.setContentView(view)
            dialog.show()
            home_image_info_btn1.isClickable = false
            dialog.setOnDismissListener {
                home_image_info_btn1.isClickable = true
            }
        }
        home_image_download_btn.setOnClickListener {
            PermissionX.init(this@ImageInfoScreen)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onExplainRequestReason { scope, deniedList ->
                    scope.showRequestReasonDialog(
                        deniedList,
                        "Core fundamental are based on these permissions",
                        "OK",
                        "Cancel"
                    )
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        saveImageToGallery(bitmap!!)
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "These permissions are denied: $deniedList",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
        home_image_download_btn1.setOnClickListener {
            PermissionX.init(this@ImageInfoScreen)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onExplainRequestReason { scope, deniedList ->
                    scope.showRequestReasonDialog(
                        deniedList,
                        "Core fundamental are based on these permissions",
                        "OK",
                        "Cancel"
                    )
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        downloadImage(itemData.imageUrl)
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "These permissions are denied: $deniedList",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        home_image_share_btn1.setOnClickListener {
            shareTextOnly(itemData.imageUrl)

        }
        home_image_edit_btn.setOnClickListener {
            shareTextOnly(itemData.imageUrl)
        }

    }
    private fun saveImageToGallery(bitmap: Bitmap) {

     val dir = File(Environment.DIRECTORY_PICTURES)
        dir.mkdirs()
        val filename = String.format("${System.currentTimeMillis()}.jpeg")
        val outfile = File(dir, filename)
        var outputstream: FileOutputStream? = null
        try {
            outputstream = FileOutputStream(outfile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputstream)
            outputstream.flush()
            outputstream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
    @SuppressLint("Range")
    private fun downloadImage(url: String) {

        val directory = File(Environment.DIRECTORY_PICTURES)

        if (!directory.exists()) {
            directory.mkdirs()
        }

        val downloadManager =
            requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadUri = Uri.parse(url)!!

        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(url.substring(url.lastIndexOf("/") + 1))
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    url.substring(url.lastIndexOf("/") + 1)
                )
        }
        var msg: String? = ""
        var lastMsg = ""


        // ...

        val downloadId = downloadManager.enqueue(request)
        val query = DownloadManager.Query().setFilterById(downloadId)
        Thread(Runnable {
            var downloading = true
            while (downloading) {
                val cursor: Cursor = downloadManager.query(query)
                cursor.moveToFirst()
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                msg = statusMessage(url, directory, status)
                if (msg != lastMsg) {
                    requireActivity().runOnUiThread {
                        Snackbar.make(requireView(), msg!!, Toast.LENGTH_SHORT).show()
                    }
                    lastMsg = msg ?: ""
                }
                cursor.close()
            }
        }).start()
    }

    private fun shareTextOnly(titlee: String) {
        val intentt = Intent(Intent.ACTION_SEND)

        intentt.type = "text/plain"
        intentt.putExtra(Intent.EXTRA_SUBJECT, "Share image")

        intentt.putExtra(Intent.EXTRA_TEXT, titlee)
        startActivity(Intent.createChooser(intentt, "Share"))
    }

    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download has been failed, please try again"
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> "Success Save to Gallery"
            else -> "There's nothing to download"
        }
        return msg
    }
}



