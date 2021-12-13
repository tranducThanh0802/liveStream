package com.example.applivestream

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applivestream.databinding.FragmentListLiveBinding
import com.example.applivestream.databinding.FragmentLiveStreamBinding
import com.example.applivestream.di.Resource
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import java.net.URI


class ListLiveActivity  : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var binding:FragmentListLiveBinding
    lateinit var ytb:YouTubePlayer
     var keychannel=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentListLiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.videoView.initialize(Resource.Key,this)
        val it=intent
        keychannel= it.getStringExtra(Resource.KEYCHANNEL).toString()
        binding.fullbtn.setOnClickListener{
            ytb.setFullscreen(true)
        }

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
            p1?.loadVideo(keychannel)
            p1?.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {

    }

}