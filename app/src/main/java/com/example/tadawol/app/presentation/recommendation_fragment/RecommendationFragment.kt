package com.example.tadawol.app.presentation.recommendation_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tadawol.R
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RecommendationsFragmentBinding
import kotlinx.android.synthetic.main.recommendations_fragment.*
import kotlinx.android.synthetic.main.recommendations_items.*


class RecommendationFragment : Fragment(){
    lateinit var viewModel: MainViewModel
    lateinit var MainAdapter: Recommendations_Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:RecommendationsFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.recommendations_fragment, container,false)

        viewModel =   ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        viewModel.updateActionBarTitle("التوصيات")

        viewModel.GetTradesData()
        viewModel.TradesResponseLD?.observe(this , Observer { it ->
            MainAdapter = Recommendations_Adapter( viewModel,context, it)
            view.recyler.layoutManager = LinearLayoutManager(context)
            view.recyler.adapter = MainAdapter;
            stoploading()
        })

    return view.root
    }
    override fun onResume() {
        super.onResume()
         shimmer_view_container.startShimmerAnimation()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmerAnimation()
        super.onPause()
    }
    fun stoploading() {
        shimmer_view_container?.setVisibility(View.GONE)
        shimmer_view_container?.stopShimmerAnimation()

    }
}