package com.riavendanot.ituneapi.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.riavendanot.ituneapi.R
import com.riavendanot.ituneapi.common.extension.loadImg
import com.riavendanot.ituneapi.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()

    private var binding: FragmentDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        initView()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initView() {
        val result = args.result
        (activity as AppCompatActivity).supportActionBar?.title = result.artistName
        binding?.let {
            it.artworkImageView.loadImg(result.artwork)
            it.albumTextView.text = result.albumName
            it.trackTextView.text = result.trackName
        }

    }
}