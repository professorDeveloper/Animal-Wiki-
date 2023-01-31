    package com.azamovhudstc.animalwiki.ui.adapter

    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.FragmentActivity
    import androidx.fragment.app.FragmentManager
    import androidx.fragment.app.FragmentStatePagerAdapter
    import androidx.viewpager2.adapter.FragmentStateAdapter
    import com.azamovhudstc.animalwiki.ui.screen.pages.ImagesPage
    import com.azamovhudstc.animalwiki.ui.screen.pages.PostsPage
    import com.azamovhudstc.animalwiki.ui.screen.pages.VideosPage


    class CategoryAdapter(var arrayList: ArrayList<String>, fragmentManager: FragmentActivity) :
        FragmentStateAdapter(fragmentManager) {


        override fun getItemCount(): Int {
            return arrayList.size
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> PostsPage()
                1 -> VideosPage()
                else -> ImagesPage()
            }

        }
    }