package com.marko.htec.interviewapp.ui.details

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.marko.htec.interviewapp.R
import com.marko.htec.interviewapp.databinding.FragmentDetailsBinding
import com.marko.htec.interviewapp.util.confStyle
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
        confStyle(activity, true, R.string.post_details)
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
            setHasOptionsMenu(viewModel.post.value!=null)

            binding.titleTxt.text = it?.title
            binding.bodyTxt.text = it?.body
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> askToDelete()
            android.R.id.home -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun askToDelete() {
        if(viewModel.post.value==null)
            return

        AlertDialog.Builder(requireContext())
            .setTitle("Delete post?")
            .setMessage(viewModel.post.value?.title)
            .setPositiveButton("Delete") { dialog, _ ->
                viewModel.deletePost()
                dialog.cancel()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}