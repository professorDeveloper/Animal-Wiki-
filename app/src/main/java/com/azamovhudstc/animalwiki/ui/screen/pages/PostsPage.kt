package com.azamovhudstc.animalwiki.ui.screen.pages

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import com.azamovhudstc.animalwiki.ui.adapter.PostsAdapter
import com.azamovhudstc.animalwiki.viewmodels.PostsScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.SplashScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.PostsScreenViewModelImp
import com.azamovhudstc.animalwiki.viewmodels.imp.SplashScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts_page.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsPage : Fragment(R.layout.fragment_posts_page),
    PostsAdapter.ContactItemCallBack.SetLongClickListener {
    private val adapter by lazy { PostsAdapter(this) }
    private val viewModel: PostsScreenViewModel by viewModels<PostsScreenViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        viewModel.postClickLiveData.observe(this, onClickObserver)
        viewModel.loadAllPostsLiveData.observe(this, getAllPostsObserver)
    }

    private val onClickObserver = Observer<WikiPost> {

        var bundle = Bundle()
        bundle.putSerializable("Keyer", it)
        findNavController().navigate(R.id.postsItemScreen, bundle)
    }
    private val getAllPostsObserver = Observer<ArrayList<WikiPost>> {
        adapter.submitList(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadAllPosts()
        lifecycleScope.launch{
            progressPost.show()
            delay(300)
            rv.adapter = adapter
            progressPost.hide()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadAllPosts()
        rv.adapter = adapter
    }

    override fun onClick(item: WikiPost) {
        viewModel.onClick(item)
    }
}