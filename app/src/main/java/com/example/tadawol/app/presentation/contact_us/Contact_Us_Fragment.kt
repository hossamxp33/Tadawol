package com.example.tadawol.app.presentation.contact_us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.Publicusecase.call
import com.example.tadawol.app.Publicusecase.openUrl
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.upgrade_fragment.Upgrade_Fragment
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.ContactUsFragmentBinding
import com.example.tadawol.databinding.RecommendationsFragmentBinding
import com.example.tadawol.databinding.UpgradeFragmentBinding
import kotlinx.android.synthetic.main.contact_us_fragment.*
import org.jetbrains.anko.support.v4.makeCall

class Contact_Us_Fragment : Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: ContactUsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.contact_us_fragment, container, false
        )
        viewModel.updateActionBarTitle("تواصل معنا")
        view.context = context as MainActivity
        view.listener = ClickHandler()
        view.textView4.setOnClickListener { call(context as MainActivity, "07721499299") }
        view.textView5.setOnClickListener { call(context as MainActivity, "07709322024") }
        view.web.setOnClickListener { openUrl(context as MainActivity) }
        return view.root
    }
}