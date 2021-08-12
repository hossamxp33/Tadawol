package com.example.tadawol.app.presentation.newsfragment

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
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NewsItemsBinding
import com.example.tadawol.databinding.RecommendationsItemsBinding


class News_Adapter (var viewModel: MainViewModel, var context : Context?, var data:List<New>) : RecyclerView.Adapter<CustomViewHolder>() {
    var isactive : Boolean ? = null


    override fun getItemCount(): Int {

        return  data.size
    }


    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val  binding: NewsItemsBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),
            R.layout.news_items,p0,false)

        return   CustomViewHolder(binding)

    }


}


class CustomViewHolder (
    public val binding:NewsItemsBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: MainViewModel, context: Context?, data:New) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
        binding.viewmodel = viewModel
    }


}
