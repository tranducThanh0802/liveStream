package com.example.applivestream

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainNavHostFragment:NavHostFragment() {
    @Inject
    lateinit var fragmentfactoryFragtory: MainFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory =fragmentfactoryFragtory
    }
}