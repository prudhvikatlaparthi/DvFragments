package com.pru.navigationcomponentdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pru.navigationcomponentdemo.FromScreen
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentPostDetailBinding

class PostDetailFragment : Fragment(R.layout.fragment_post_detail) {

    private lateinit var binding: FragmentPostDetailBinding
    private val arguments by navArgs<PostDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostDetailBinding.bind(view)
        binding.tvTitle.append(" ".plus(arguments.userID.toString()))
        binding.btnNav.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSettings.setOnClickListener {
            val action = SettingsFragmentDirections.actionGlobalSettingsFragment(fromScreen = FromScreen.PostDetailScreen)
            findNavController().navigate(action)
        }
    }
}