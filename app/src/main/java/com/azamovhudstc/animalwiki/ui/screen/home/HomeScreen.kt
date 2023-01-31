package com.azamovhudstc.animalwiki.ui.screen.home

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.sharedpref.AppReference
import com.azamovhudstc.animalwiki.ui.adapter.CategoryAdapter
import com.azamovhudstc.animalwiki.utils.extensions.showSnack
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.about_dialog.view.*
import kotlinx.android.synthetic.main.exit_dialog.view.*
import kotlinx.android.synthetic.main.home_screen.*
import kotlinx.android.synthetic.main.tab_item.view.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.home_screen) {
    lateinit var categoryList: ArrayList<String>
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawerLayout = view.findViewById(R.id.drawerLayout)
        categoryList = ArrayList<String>()
        categoryList.add("Maqolalar")
        categoryList.add("Videolar")
        categoryList.add("Rasmlar")
        var categoryAdapter =
            CategoryAdapter(categoryList, requireActivity())
        view_pager.adapter = categoryAdapter
        view_pager.isUserInputEnabled = false
        view_pager.setOnTouchListener(null);
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->

        }.attach()
        showSnack("asdasd")
        navigations.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)

        }
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tab_main?.setBackgroundResource(R.drawable.bg_item_tab)
                customView?.title_tv?.setTextColor(requireActivity().getColor(R.color.white))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tab_main?.setBackgroundResource(R.drawable.bg_item_tab_un)
                customView?.title_tv?.setTextColor(requireActivity().getColor(R.color.blackS))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        setTab()
        navigation.setNavigationItemSelectedListener { item ->
            when (item?.itemId) {
                R.id.quit -> {
                    var  alert= Dialog(requireContext())
                    val inflater = LayoutInflater.from(requireContext())
                    var view = inflater.inflate(R.layout.exit_dialog,null)
                    alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alert.setContentView(view)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    view.yoq.setOnClickListener {
                        alert.dismiss()
                    }
                    view.chiqish.setOnClickListener {
                        requireActivity().finish()
                        System.out.close()
                    }
                    alert.show()
                    drawerLayout.closeDrawer(GravityCompat.START)

                }

                R.id.rating -> {
                    val url = "https://play.google.com/store/apps/details?id=com.azamovhudstc.animalwiki"
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.share -> {
                    val sharingIntent = Intent(Intent.ACTION_SEND)

                    // type of the content to be shared

                    // type of the content to be shared
                    sharingIntent.type = "text/plain"

                    // Body of the content

                    // Body of the content
                    val shareBody = "Dictionary App Sharing With"

                    // subject of the content. you can share anything

                    // subject of the content. you can share anything
                    val shareSubject = "https://play.google.com/store/apps/details?id=com.azamovhudstc.animalwiki"

                    // passing body of the content

                    // passing body of the content
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareSubject)

                    // passing subject of the content

                    // passing subject of the content
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
                    startActivity(Intent.createChooser(sharingIntent, "Share using"))
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.info -> {
                    var  alert= Dialog(requireContext())
                    val inflater = LayoutInflater.from(requireContext())
                    var view = inflater.inflate(R.layout.about_dialog,null)
                    alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    alert.setContentView(view)
                    view.closeAboutDialog.setOnClickListener {
                        alert.dismiss()
                    }
                    drawerLayout.closeDrawer(GravityCompat.START)
                    alert.show()
                }

            }
            true
        };

    }

    private fun setTab() {
        val tabCount = tab_layout.tabCount
        for (i in 0 until tabCount) {


            val tabView =
                LayoutInflater.from(requireActivity()).inflate(R.layout.tab_item, null, false)
            val tab = tab_layout.getTabAt(i)


            tab?.customView = tabView
            tabView.title_tv.text = categoryList[i]
            if (i == 0) {
                tabView?.tab_main?.setBackgroundResource(R.drawable.bg_item_tab)
                tabView?.title_tv?.setTextColor(requireActivity().getColor(R.color.white))
            } else {
                tabView?.tab_main?.setBackgroundResource(R.drawable.bg_item_tab_un)
                tabView?.title_tv?.setTextColor(requireActivity().getColor(R.color.blackS))
            }
        }
    }


}