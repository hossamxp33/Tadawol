package com.example.tadawol.app.presentation.stock_price

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.Publicusecase.showToastBasedOnThrowable
import com.example.tadawol.app.models.New
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NewsFragmentBinding
import com.example.tadawol.databinding.StockPriceFragmentBinding
import kotlinx.android.synthetic.main.recommendations_fragment.*
import java.util.*
import kotlin.collections.ArrayList


class StockPriceFragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    lateinit var MainAdapter: Stock_Price_Adapter

     var data : New ? = null





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:StockPriceFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.stock_price_fragment, container,false)

        viewModel.updateActionBarTitle("أخبار الاقتصاد العالمي والمحلي")

        view.context = context as MainActivity
        viewModel.GetStockPrice()

        viewModel.StockPricesResponseLD?.observe(this , Observer { it ->
            MainAdapter =    Stock_Price_Adapter(viewModel,activity!!, it)
            recyler.layoutManager = LinearLayoutManager(context)
            recyler.adapter = MainAdapter;
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