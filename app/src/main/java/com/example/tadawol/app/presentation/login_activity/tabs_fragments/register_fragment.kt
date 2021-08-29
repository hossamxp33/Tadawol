package com.example.tadawol.app.presentation.login_activity.tabs_fragments

import android.annotation.SuppressLint
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
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RegisterFragmentBinding
import com.example.tadawol.databinding.SignInFragmentBinding
import www.sanju.motiontoast.MotionToast

class register_fragment : Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    @SuppressLint("HardwareIds")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: RegisterFragmentBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.register_fragment, container, false
            )

        view.btnLogin.setOnClickListener {
            val android_id = Settings.Secure.getString(
                activity!!.getContentResolver(),
                Settings.Secure.ANDROID_ID
            )
            viewModel.userRegister(
                view.etUsername.text.toString(),
                view.mobile.text.toString(),
                android_id
            )

        }
        //// Should be requireActivity() Be Cause this is Fragment
        viewModel.RegisterResponseLD?.observe(requireActivity(), Observer {
            if (!(view.etUsername.text.isEmpty() || view.mobile.text.isEmpty())) {
                if (it.success) {

                    SUCCESS_MotionToast("تم التسجيل بنجاح",activity!!)
                    val homeIntent = Intent(context, MainActivity()::class.java)
                    (context as Login).startActivity(homeIntent)
                } else {
                    ERROR_MotionToast("الرجاء ادخال البيانات صحيحة",activity!!)

                }
            } else {
                ERROR_MotionToast("خطا",activity!!)
            }

        })

        viewModel.errorLivedat.observe(this, Observer {
            ERROR_MotionToast("خطا",activity!!)
        })

        return view.root
    }

}