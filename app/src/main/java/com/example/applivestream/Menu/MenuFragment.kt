package com.example.applivestream.Menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.applivestream.R
import com.example.applivestream.databinding.FramentMenuBinding


class MenuFragment : Fragment() {
    lateinit var binding: FramentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FramentMenuBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLive.setOnClickListener{

            findNavController().navigate(R.id.action_menuFragment_to_setRTMPFragment)
        }
        binding.buttonWatch.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_setNameFragment)
        }
    }


}