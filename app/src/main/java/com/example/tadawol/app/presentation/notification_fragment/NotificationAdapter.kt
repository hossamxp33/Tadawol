package com.example.tadawol.presentaion.notification_fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import android.R.attr.button
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.models.Notification_Data
import com.example.tadawol.app.presentation.ClickHandler

import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NotificationAdapterBinding
import org.jetbrains.anko.matchParent


class NotificationAdapter (var viewModel: MainViewModel, var context :Context?, var data:List<Notification_Data>) : RecyclerView.Adapter<CustomViewHolders>() {
    override fun getItemCount(): Int {

        return  data.size

    }

    override fun onBindViewHolder(p0: CustomViewHolders, p1: Int) {
        p0.bind(viewModel,context,data.get(p1),viewModel,p1)


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolders {
//        val layoutInflater  = LayoutInflater.from(p0.context);
//        val cellforrow = layoutInflater.inflate(R.layout.main_adapter,p0,false);

//        val layoutParams = cellforrow.getLayoutParams()
//        layoutParams.height = (p0.getHeight() /  2).toInt()
//        layoutParams.width = (p0.getWidth() /  2.5).toInt()
//        cellforrow.setLayoutParams(layoutParams)
        val  binding: NotificationAdapterBinding = DataBindingUtil.inflate (LayoutInflater.from(p0.context),
            R.layout.notification_adapter,p0,false)

        return  CustomViewHolders(binding)
    }


}
class CustomViewHolders (
    public val binding: NotificationAdapterBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(viewModel:MainViewModel,context: Context?,data:Notification_Data,viewModels: MainViewModel,int: Int ) {

        binding.listener = ClickHandler()
        binding.viewmodel = viewModels
        binding.data = data
        binding.context = context as MainActivity?
    }

}