package com.example.applivestream.Feature

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.applivestream.WatchLiveActivity
import com.example.applivestream.databinding.FragmentSetNameBinding
import com.example.applivestream.di.Resource


class SetNameFragment : Fragment() {
    lateinit var binding: FragmentSetNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSetNameBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startBroadcastButton.setOnClickListener{
            if(!binding.topicEdit.text.isNullOrEmpty()){
               val intent=Intent(requireActivity(), WatchLiveActivity::class.java)
                intent.putExtra(Resource.KEYCHANNEL,binding.topicEdit.text.toString())
                startActivity(intent)
            }
        }
    }


}