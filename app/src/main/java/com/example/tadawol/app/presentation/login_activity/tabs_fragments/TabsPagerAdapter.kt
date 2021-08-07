package com.example.tadawol.app.presentation.login_activity.tabs_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tadawol.app.presentation.login_activity.tabs_fragments.register_fragment
import com.example.tadawol.app.presentation.login_activity.tabs_fragments.sign_in_fragment

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // # Music Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Music Fragment")
                val registerFragment =
                    register_fragment()
                registerFragment.arguments = bundle
                return registerFragment
            }
            1 -> {
                // # Movies Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Movies Fragment")
                val sign_inFragment =
                    sign_in_fragment()
                sign_inFragment.arguments = bundle
                return sign_inFragment
            }

            else -> return sign_in_fragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}