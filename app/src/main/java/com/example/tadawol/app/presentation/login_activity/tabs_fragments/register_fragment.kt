package com.example.tadawol.app.presentation.login_activity.tabs_fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class register_fragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:RegisterFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.register_fragment, container,false)
        var  android_id = Settings.Secure.getString(activity!!.getContentResolver(),
            Settings.Secure.ANDROID_ID);
        view.btnLogin.setOnClickListener {

            viewModel.userRegister(view.etUsername.text.toString(), view.email.text.toString(),android_id)

        }

        //// Should be requireActivity() Be Cause this is Fragment
        viewModel.RegisterResponseLD?.observe(requireActivity(), Observer {
            if (it.token != null) {
//                PreferenceHelper.setUserId(it.userid)
//                PreferenceHelper.setToken(it.token,activity)
//                PreferenceHelper.setUserGroupId(it.groupid)

                if (!(view.etUsername.text.isEmpty() || view.etUsername.text.isEmpty()))
                {
                    Toast.makeText(context, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show()

                    val homeIntent = Intent(context, MainActivity()::class.java)
                    (context as Login).startActivity(homeIntent)
                }
            }
            else{
                Toast.makeText(context, "خطأ بكلمة المرور او اسم المستخدم", Toast.LENGTH_SHORT).show()
            }

        })

    return view.root
    }

}