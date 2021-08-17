package com.example.tadawol.app.presentation.newsfragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.models.New
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NewsDetailsBinding


class Details_News_Fragment:Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    var data : New? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.updateActionBarTitle("تفاصيل الخبر")

        var view: NewsDetailsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.news_details, container, false
            )
         data = arguments?.getParcelable("news_data")
         view.data = data
         view.textView6.setMovementMethod(ScrollingMovementMethod())


        return view.root
    }
}