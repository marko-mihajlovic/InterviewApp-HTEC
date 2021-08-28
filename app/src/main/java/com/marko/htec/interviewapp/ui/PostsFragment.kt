package com.marko.htec.interviewapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.marko.htec.interviewapp.adapter.PostsAdapter
import com.marko.htec.interviewapp.databinding.FragmentPostsBinding

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private val viewModel: PostsViewModel by activityViewModels()
    private var newsAdapter: PostsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPostsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confListAndAdapter()
        confViewModel()

    }

    private fun confListAndAdapter() {
        newsAdapter = PostsAdapter()
        binding.listView.adapter = newsAdapter
    }

    private fun confViewModel() {
        viewModel.postList.observe(viewLifecycleOwner, {
            newsAdapter?.updateList(it)
        })
    }



}