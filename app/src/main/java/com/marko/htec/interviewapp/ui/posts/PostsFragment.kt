package com.marko.htec.interviewapp.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.marko.htec.interviewapp.R
import com.marko.htec.interviewapp.adapter.PostsAdapter
import com.marko.htec.interviewapp.databinding.FragmentPostsBinding
import com.marko.htec.interviewapp.util.confStyle
import com.marko.htec.interviewapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@AndroidEntryPoint
class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private val viewModel: PostsViewModel by activityViewModels()
    @Inject lateinit var postsAdapter: PostsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPostsBinding.inflate(layoutInflater, container, false)
        confStyle(activity, false, R.string.android_task)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confUI()
        confViewModel()

    }

    private fun confUI() {
        binding.listView.adapter = postsAdapter

        binding.refreshListLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun confViewModel() {
        viewModel.postList.observe(viewLifecycleOwner, {
            postsAdapter.updateList(it)
        })

        viewModel.toastMsg.value = ""
        viewModel.toastMsg.observe(viewLifecycleOwner, {
            showToast(context, it)
        })

        viewModel.isRefreshing.value = false
        viewModel.isRefreshing.observe(viewLifecycleOwner, {
            binding.refreshListLayout.post {
                binding.refreshListLayout.isRefreshing = it
            }
        })
    }



}