package com.example.applivestream

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class MainFragmentFactory @Inject constructor() :FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
                  LiveStreamFragment::class.java.name ->{
                      LiveStreamFragment()
                  }
                else ->super.instantiate(classLoader, className)
        }

    }
}