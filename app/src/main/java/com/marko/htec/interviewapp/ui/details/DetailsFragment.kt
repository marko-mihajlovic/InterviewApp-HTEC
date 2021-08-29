package com.marko.htec.interviewapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.marko.htec.interviewapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confViewModel()

    }

    private fun confViewModel(){
        viewModel.user.observe(viewLifecycleOwner, {
            binding.nameTxt.text = it?.name
            binding.emailTxt.text = it?.email
        })

        viewModel.post.observe(viewLifecycleOwner, {
            binding.titleTxt.text = it?.title
            binding.bodyTxt.text = it?.body
        })
    }

}