package com.example.tadawol.app.presentation.user_setting

import android.content.Intent
import android.os.Bundle
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
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.login_activity.Login
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RegisterFragmentBinding
import com.example.tadawol.databinding.SettingFragmentBinding
import com.example.tadawol.databinding.SignInFragmentBinding

class Setting_Fragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:SettingFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.setting_fragment, container,false)

        view.context = context as MainActivity
        view.listener = ClickHandler()
        view.username.text = PreferenceHelper.getUsername()

    return view.root
    }

}