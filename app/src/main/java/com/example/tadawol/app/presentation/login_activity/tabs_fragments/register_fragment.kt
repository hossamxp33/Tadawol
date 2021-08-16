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
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.presentation.login_activity.Login
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RegisterFragmentBinding
import com.example.tadawol.databinding.SignInFragmentBinding
import www.sanju.motiontoast.MotionToast

class register_fragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    @SuppressLint("HardwareIds")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:RegisterFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.register_fragment, container,false)

        view.btnLogin.setOnClickListener {
            var  android_id = Settings.Secure.getString(activity!!.getContentResolver(),
                Settings.Secure.ANDROID_ID)
            viewModel.userRegister(view.etUsername.text.toString(), view.mobile.text.toString(),android_id)

        }
        //// Should be requireActivity() Be Cause this is Fragment
        viewModel.RegisterResponseLD?.observe(requireActivity(), Observer {
            if (!(view.etUsername.text.isEmpty() || view.mobile.text.isEmpty()))
            {
            if (it.success) {

                    Toast.makeText(context, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show()
                    val homeIntent = Intent(context, MainActivity()::class.java)
                    (context as Login).startActivity(homeIntent)
                }else {
                    MotionToast.createColorToast(activity!!,
                        "Hurray success 😍",
                        " الرجاء أدخل البيانات صحيحة",
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_TOP,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(activity!!, R.font.helvetica_regular))
                }
            }
            else{
                Toast.makeText(context, "خطأ", Toast.LENGTH_SHORT).show()
            }

        })

        viewModel.errorLivedat.observe(this, Observer {
            MotionToast.createColorToast(activity!!,
                "Hurray success 😍",
                it,
                MotionToast.TOAST_ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(activity!!, R.font.helvetica_regular))
        })

    return view.root
    }

}