package com.example.tadawol.app.DataRepo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.example.tadawol.app.data_layer.APIServices
import com.example.tadawol.app.data_layer.ApiClient
import com.example.tadawol.app.models.MainTrades
import com.example.tadawol.app.models.Trade


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.function.BiFunction


class  DataRepo {

///// Get Tadawol  Page Data
//////////////GetTrancsactionReportChartForOffice
@SuppressLint("CheckResult")
fun GetTradesData(livedata: MutableLiveData<List<Trade>>?) {
    getServergetway().MyTrades()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { data -> data }
        .subscribe(
            { books ->
                livedata?.postValue(books.trades)
            },
            { error ->

            }
        )
}




    }
    fun getServergetway () : APIServices
    {
        return ApiClient.client!!.create(APIServices::class.java)
    }


