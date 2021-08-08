package com.example.tadawol.app.presentation

import android.content.Context
import androidx.fragment.app.FragmentTransaction
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment

class ClickHandler {


    fun SwitchToRecommends( context: Context) {

        val recommendation_fragment = RecommendationFragment()
        ( context as MainActivity). supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, recommendation_fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }


}