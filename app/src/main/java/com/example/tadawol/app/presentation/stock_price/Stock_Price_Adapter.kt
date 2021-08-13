package com.example.tadawol.app.presentation.stock_price

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.models.New
import com.example.tadawol.app.models.Price
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NewsItemsBinding
import com.example.tadawol.databinding.RecommendationsItemsBinding
import com.example.tadawol.databinding.StockPriceAdapterBinding


class Stock_Price_Adapter (var viewModel: MainViewModel, var context : Context?, var data:List<Price>) : RecyclerView.Adapter<CustomViewHolder>() {
    var isactive : Boolean ? = null


    override fun getItemCount(): Int {

        return  data.size
    }


    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))
        if(p1 %2 == 1) {
            p0.binding.constraintLayout.setBackgroundResource(R.color.lightGrey)
        }else{
            p0.binding.constraintLayout.setBackgroundResource(R.color.white)

        }

        }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val  binding: StockPriceAdapterBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),
            R.layout.stock_price_adapter,p0,false)

        return   CustomViewHolder(binding)

    }


}


class CustomViewHolder (
    public val binding:StockPriceAdapterBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: MainViewModel, context: Context?, data:Price) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
        binding.viewmodel = viewModel
    }


}
