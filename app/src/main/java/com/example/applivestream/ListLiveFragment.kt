package com.example.applivestream

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applivestream.databinding.FragmentListLiveBinding
import com.example.applivestream.databinding.FragmentLiveStreamBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import java.net.URI


class ListLiveFragment constructor() : YouTubePlayerFragment(), YouTubePlayer.OnInitializedListener {

    lateinit var binding:FragmentListLiveBinding
    lateinit var ytb:YouTubePlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentListLiveBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.videoView.initialize("AIzaSyDZI2iQobEUlXauR9IUgExsIOOWDKBT_SY",this)
        ytb.setFullscreen(true)
    }
    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if (p1 != null) {
            ytb=p1
        }
        if(!p2)
        {
            p1?.loadVideo("DXqVOi9v39k")
            p1?.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {

    }

}