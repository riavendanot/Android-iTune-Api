package com.riavendanot.ituneapi.detail

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.riavendanot.ituneapi.R
import com.riavendanot.ituneapi.common.ITunesViewModelFactory
import com.riavendanot.ituneapi.common.TrackState
import com.riavendanot.ituneapi.common.extension.loadImg
import com.riavendanot.ituneapi.databinding.FragmentDetailBinding
import com.riavendanot.ituneapi.detail.adapter.TrackAdapter
import com.riavendanot.ituneapi.domain.entity.TrackDto

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels {
        ITunesViewModelFactory(ITunesViewModelFactory.ViewModelType.DETAIL)
    }
    private val args: DetailFragmentArgs by navArgs()
    private val trackAdapter = TrackAdapter()

    private var binding: FragmentDetailBinding? = null
    private val player: MediaPlayer = MediaPlayer()

    private val observerProgress = Observer<Boolean> {
        binding?.detailProgress?.visibility = if(it) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private val observerTrackList = Observer<List<TrackDto>?> {
        if (!it.isNullOrEmpty()) {
            trackAdapter.addItems(it)
        }
    }

    private val observerTrackState = Observer<TrackState> {
        if (it != null) {
            when(it) {
                TrackState.PLAY -> {
                    binding?.playPauseButton?.text = "Stop"
                    playAudio() }
                else -> {
                    binding?.playPauseButton?.text = "Play"
                    stopAudio()}
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        viewModel.loader.observe(viewLifecycleOwner, observerProgress)
        viewModel.list.observe(viewLifecycleOwner, observerTrackList)
        viewModel.trackState.observe(viewLifecycleOwner, observerTrackState)
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
        }
        binding?.songsRecyclerView?.apply {
            val linearManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            layoutManager = linearManager
            adapter = trackAdapter
            val dividerItemDecorator = DividerItemDecoration(context,
                linearManager.orientation)
            addItemDecoration(dividerItemDecorator)
        }
        binding?.playPauseButton?.setOnClickListener { viewModel.playOrPause() }
        viewModel.getTracks(result.albumId)
    }

    private fun playAudio() {
        //TODO -> play audio
        /*val url = viewModel.list.value?.get(0)?.url
        player.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build())
        player.prepare()
        player.start()
        try {
            player.setDataSource(url)
        } catch (e: IOException) {
            Log.d("DetailFrament", "${e.message}")
        }*/
    }

    private fun stopAudio() {
        /*if (player.isPlaying){
            player.stop()
            player.reset()
            player.release()
        }*/
    }


}