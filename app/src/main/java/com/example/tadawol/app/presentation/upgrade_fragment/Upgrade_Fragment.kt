package com.example.tadawol.app.presentation.upgrade_fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.models.New
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NewsDetailsBinding
import com.example.tadawol.databinding.UpgradeFragmentBinding


open class Upgrade_Fragment:Fragment() {
    val viewModel : MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    var data : New? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: UpgradeFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.upgrade_fragment, container, false)
        viewModel.updateActionBarTitle("ترقية")

        return view.root
    }
}