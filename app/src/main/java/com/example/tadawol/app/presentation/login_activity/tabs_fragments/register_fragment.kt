package com.example.tadawol.app.presentation.login_activity.tabs_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tadawol.R
import com.example.tadawol.databinding.RegisterFragmentBinding
import com.example.tadawol.databinding.SignInFragmentBinding

class register_fragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:RegisterFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.register_fragment, container,false)



    return view.root
    }

}