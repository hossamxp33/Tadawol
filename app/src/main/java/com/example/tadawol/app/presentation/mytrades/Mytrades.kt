package com.example.tadawol.app.presentation.mytrades

import android.provider.Settings
import androidx.core.content.res.ResourcesCompat
import com.example.tadawol.R
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment
import com.example.tadawol.databinding.RecommendationsFragmentBinding
import www.sanju.motiontoast.MotionToast

class Mytrades : RecommendationFragment() {

 override fun getdata(view: RecommendationsFragmentBinding) {
     viewModel.GetTradesDataForUser(page)
     var  android_id = Settings.Secure.getString(activity!!.getContentResolver(),
         Settings.Secure.ANDROID_ID);

println(android_id)
 }
}