package com.azamovhudstc.animalwiki.ui.screen.posts

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.animalwiki.R
import com.azamovhudstc.animalwiki.data.local.models.WikiPost
import com.azamovhudstc.animalwiki.data.local.models.WikiPostData
import com.azamovhudstc.animalwiki.ui.adapter.PostItemsAdapter
import com.azamovhudstc.animalwiki.viewmodels.PostItemScreenViewModel
import com.azamovhudstc.animalwiki.viewmodels.imp.PostItemsScreenViewModelImp
import com.leo.materialsearchview.MaterialSearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_posts_item_screen.*
import kotlinx.android.synthetic.main.fragment_posts_page.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class PostsItemScreen : Fragment(R.layout.fragment_posts_item_screen),
    PostItemsAdapter.ContactItemCallBack.SetLongClickListener {
    private val materialSearchView by lazy { MaterialSearchView(requireContext()) }

    private val viewModel: PostItemScreenViewModel by viewModels<PostItemsScreenViewModelImp>()
    private val adapter by lazy { PostItemsAdapter(this) }
     var list: ArrayList<WikiPostData>  = ArrayList()

    private val loadPostItemsObserver = Observer<ArrayList<WikiPostData>> {
        list.addAll(it)
        adapter.submitList(it)
    }
    private val onClickObserver = Observer<WikiPostData> {
        var bundle = Bundle()
        bundle.putSerializable("Keyer", it)
        findNavController().navigate(R.id.postItemInfoScreen, bundle)
        materialSearchView.dismiss()
        list.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onClickLiveData.observe(this, onClickObserver)
        viewModel.postItemsLiveData.observe(this, loadPostItemsObserver)
    }

    private fun filter(text: String) {
        if (text.isNotEmpty()) {

            val filteredlist = ArrayList<WikiPostData>()

            // running a for loop to compare elements.
            for (item in list) {
                // checking if the entered string matched with any item of our recycler view.
                if (
                    item.title.toLowerCase()
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var wikiPost = arguments?.getSerializable("Keyer") as WikiPost
        type_rv.text = wikiPost.wikiName
        viewModel.getPostItems(wikiPost.types)
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
        searchPosts.setOnClickListener {
            materialSearchView.show(searchPosts)
        }
        materialSearchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
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
                }, 500)
                return true
            }
        })

        lifecycleScope.launch {
            progressPostItems.show()
            delay(400)
            rv_post_items.adapter = adapter
            progressPostItems.hide()
        }
        backPostScreen.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    //    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        requireActivity().menuInflater.inflate(R.menu.search_view, menu)
//        val item=menu.findItem(R.id.action_search)
//        var searchView=item?.actionView as SearchView
//        val searchClose: ImageView = searchView.findViewById<View>(androidx.appcompat.R.id.search_close_btn) as ImageView
//        searchClose.setColorFilter(R.color.white,android.graphics.PorterDuff.Mode.MULTIPLY);
//
//        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Log.d("!@#", "onQueryTextChange: $query")
//                return true
//
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                filter(newText!!)
//                Log.d("!@#", "onQueryTextChange: $newText")
//                return true
//            }
//        })
//        super.onCreateOptionsMenu(menu, inflater)
//
//    }
    override fun onClick(item: WikiPostData) {
        viewModel.onClick(wikiPostData = item)
    }
}