package com.example.applivestream.Feature

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.applivestream.R
import com.example.applivestream.databinding.FragmentSetRtmpBinding
import com.example.applivestream.di.Resource
import pub.devrel.easypermissions.EasyPermissions


class SetRTMPFragment : Fragment() {
    private lateinit var binding: FragmentSetRtmpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSetRtmpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hasPermissions()
        binding.btnLive.setOnClickListener {
            if (!binding.rtmpEdit.text.isNullOrEmpty()) {
                val bundle = bundleOf(Resource.KEYLIVE to binding.rtmpEdit.text.toString())
                findNavController().navigate(
                    R.id.action_setRTMPFragment_to_liveStreamFragment,
                    bundle
                )
            }
        }
    }

    private fun hasPermissions() {
        if (Resource.hasPermissions(requireContext())) {
            return
        }
        EasyPermissions.requestPermissions(
            requireActivity(), "You need to accept permissions to use this app",
            Resource.Request_CODE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onResume() {
        super.onResume()
        val flagFullscreen = WindowManager.LayoutParams.FLAG_FULLSCREEN
        requireActivity().window.clearFlags(flagFullscreen)
    }
}