package com.example.tadawol.app.presentation.viewmodel

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import android.graphics.Color
import androidx.lifecycle.LiveData
import com.example.tadawol.R
import com.example.tadawol.app.DataRepo.DataRepo
import com.example.tadawol.app.models.MainTrades
import com.example.tadawol.app.models.Trade
import io.reactivex.disposables.CompositeDisposable


@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
@BindingAdapter("app:imageResourcee")
fun setImageResourcee(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}


class MainViewModel : ViewModel() {

    var DateRepoCompnay: DataRepo = DataRepo()
     var mCompositeDisposable = CompositeDisposable()


    var TradesResponseLD : MutableLiveData<List<Trade>>? = null
     var main_title = MutableLiveData<String>()


    init {

        TradesResponseLD = MutableLiveData()
        main_title = MutableLiveData()
    }



    fun  GetTradesData(){
        DateRepoCompnay.GetTradesData(TradesResponseLD)
    }

    fun updateActionBarTitle(title: String){
        main_title.postValue(title)
    }
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}