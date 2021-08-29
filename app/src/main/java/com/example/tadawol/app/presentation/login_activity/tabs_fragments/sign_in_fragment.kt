package com.example.tadawol.app.presentation.login_activity.tabs_fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.Publicusecase.ERROR_MotionToast
import com.example.tadawol.app.Publicusecase.SUCCESS_MotionToast
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.presentation.login_activity.Login
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RegisterFragmentBinding
import com.example.tadawol.databinding.SignInFragmentBinding
import org.jetbrains.anko.support.v4.act
import www.sanju.motiontoast.MotionToast

class sign_in_fragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: SignInFragmentBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.sign_in_fragment, container,false)

        view.btnLogin.setOnClickListener {
            var  android_id = Settings.Secure.getString(activity!!.getContentResolver(),
                Settings.Secure.ANDROID_ID);

            viewModel.Login(view.etUsername.text.toString(), android_id)

        }

        //// Should be requireActivity() Be Cause this is Fragment
        viewModel.LoginResponseLD?.observe(requireActivity(), Observer {
            if (it.token != null && it.mobile != null) {
                PreferenceHelper.setToken(it.token,activity)
                 PreferenceHelper.setUserId(it.userid!!)
                PreferenceHelper.setUsername(it.username!!)
                PreferenceHelper.setUserGroupId(it.groupid!!)

                if (!(view.etUsername.text.isEmpty()))
                {
                    SUCCESS_MotionToast ("تم تسجيل الدخول",activity!!)

                    val homeIntent = Intent(context,MainActivity()::class.java)
                    (context as Login).startActivity(homeIntent)

             }
            }else{
                ERROR_MotionToast("Error in data",activity!!)
            }


        })

        viewModel.errorLivedat.observe(this, Observer {
            ERROR_MotionToast("Error in data",activity!!)
        })
        return view.root
    }



    ////// ERROR Toast

}