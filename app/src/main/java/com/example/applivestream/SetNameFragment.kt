package com.example.applivestream

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.applivestream.databinding.FragmentSetNameBinding


class SetNameFragment : Fragment() {
    lateinit var binding: FragmentSetNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSetNameBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startBroadcastButton.setOnClickListener{
            if(!binding.topicEdit.text.isNullOrEmpty()){
                findNavController().navigate(R.id.action_setNameFragment_to_listLiveFragment)
            }
        }
    }


}