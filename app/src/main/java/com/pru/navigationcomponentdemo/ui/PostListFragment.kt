package com.pru.navigationcomponentdemo.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentPostListBinding

class PostListFragment : Fragment(R.layout.fragment_post_list) {

    private lateinit var binding: FragmentPostListBinding
    private val postViewModel by viewModels<PostViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostListBinding.bind(view)
        binding.btnNav.setOnClickListener {
            postViewModel.addData("Happy")
        }

        postViewModel.data.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { pk ->
                Toast.makeText(requireContext(), pk, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnSettings.setOnClickListener {
            /*val action = MyDialogFragmentDirections.actionGlobalMyDialogFragment()
            findNavController().navigate(action)*/
            val action =
                PostListFragmentDirections.actionPostListFragmentToPostDetailFragment(userID = (0 until 10).random())
            findNavController().navigate(action)
        }
    }
}