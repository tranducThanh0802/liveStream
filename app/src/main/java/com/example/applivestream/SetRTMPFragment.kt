package com.example.applivestream

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.applivestream.databinding.FragmentSetNameBinding
import com.example.applivestream.databinding.FragmentSetRtmpBinding
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import javax.inject.Inject


class SetRTMPFragment : Fragment() {
    private lateinit var binding:FragmentSetRtmpBinding
    private lateinit var rtmpCamera1: RtmpCamera1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSetRtmpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLive.setOnClickListener{
            if(!binding.rtmpEdit.text.isNullOrEmpty()){
                val bundle = bundleOf("key" to binding.rtmpEdit.text.toString())
                findNavController().navigate(R.id.action_setRTMPFragment_to_liveStreamFragment,bundle)

            }
        }
    }
}