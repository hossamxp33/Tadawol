package com.example.tadawol.app.presentation.upgrade_fragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.models.Data
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RecommendationsItemsBinding
import com.example.tadawol.databinding.UpGradeItemBinding
import java.lang.Exception


class Subscriptions_Adapter (var viewModel: MainViewModel, var context : Context?, var data:List<Data>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        p0.bind(viewModel,context,data.get(p1))






    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val  binding: UpGradeItemBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),
            R.layout.up_grade_item,p0,false)

        return   CustomViewHolder(binding)

    }


}


class CustomViewHolder (
    public val binding:UpGradeItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel: MainViewModel, context: Context?, data:Data) {

        binding.listener = ClickHandler()
        binding.data = data
        binding.context = context as MainActivity?
        binding.viewmodel = viewModel
    }


}
