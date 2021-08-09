package com.example.tadawol.app.presentation.recommendation_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RecommendationsItemsBinding


class Recommendations_Adapter (var viewModel: MainViewModel, var context : Context?, var data:List<Trade>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {

        return  data.size
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))
        if (data.get(p1).vips == "1"){

           p0.binding.blurLayout.startBlur()
            p0. binding.blurLayout.pauseBlur()

            p0.binding.blurLayout.visibility = View.VISIBLE

        }else{
            p0.binding.blurLayout.visibility = View.GONE

        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val  binding: RecommendationsItemsBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),
            R.layout.recommendations_items,p0,false)

        return  CustomViewHolder(binding)

    }


}


class CustomViewHolder (
    public val binding:RecommendationsItemsBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: MainViewModel, context: Context?, data:Trade) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
        binding.viewmodel = viewModel
    }


}
