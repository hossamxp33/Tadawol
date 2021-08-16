package com.example.tadawol.app.DataRepo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData

import com.example.tadawol.app.data_layer.APIServices
import com.example.tadawol.app.data_layer.ApiClient
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.models.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class  DataRepo {



    ////////////Login
    @SuppressLint("CheckResult")
    fun userlogin(username:String, password:String, livedata: MutableLiveData<LoginData>?, errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {
        getServergetway().userlogin(username,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->

                    livedata?.postValue(books.data)
                    loadingLivedata.postValue(false)

                },
                {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )
    }

    ///userRegister
    @SuppressLint("CheckResult")
    fun userRegister(username:String, mobile:String,password:String, livedata: MutableLiveData<RegisterModel>?, errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {
        getServergetway().userRegister(username,mobile,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(  
                { books ->

                    livedata?.postValue(books)
                    loadingLivedata.postValue(false)

                },
                {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )
    }
    @SuppressLint("CheckResult")
    fun GetTradesDataForUser(page:Int, livedata: MutableLiveData<MainTrades>?,errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {
        getServergetway().MyTradesForUser(PreferenceHelper.getUserId(),page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->

                    livedata?.postValue(books)
                    loadingLivedata.postValue(false)

                },
                {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )
    }

    ///// Get Tadawol  Page Data
@SuppressLint("CheckResult")
fun GetTradesData(page:Int, livedata: MutableLiveData<MainTrades>?,errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {
    getServergetway().MyTrades(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { data -> data }
        .subscribe(
            { books ->

                livedata?.postValue(books)
                loadingLivedata.postValue(false)

            },
            {
                errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
            }
        )
}
    @SuppressLint("CheckResult")
    fun GetProfitData(livedata: MutableLiveData<Profit>?,errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {
        getServergetway().MyProfit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->

                    livedata?.postValue(books)
                    loadingLivedata.postValue(false)

                },
                {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )
    }


    ////Currencies
    @SuppressLint("CheckResult")
    fun GetCurrenciesData(livedata: MutableLiveData<List<Data>>?,errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {
        getServergetway().Currencies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->

                    livedata?.postValue(books.data)
                    loadingLivedata.postValue(false)

                },
                {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )
    }
/////GetStockPrice
@SuppressLint("CheckResult")
fun GetStockPrice(livedata: MutableLiveData<List<Price>>?,errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {
   
    getServergetway().GetStockPrice()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { data -> data }
        .subscribe(
            { books ->

                livedata?.postValue(books.obj.prices)

                loadingLivedata.postValue(false)

            },
            {
                errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
            }
        )


}

    ////MyNews
@SuppressLint("CheckResult")

fun GetMyNewsData(livedata: MutableLiveData<List<New>>?,errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {

    getServergetway().MyNews()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { data -> data }
        .subscribe(
            { books ->

                livedata?.postValue(books.news)
                loadingLivedata.postValue(false)

            },
            {
                errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
            }
        )
}

    //////Add
    @SuppressLint("CheckResult")
    fun Add(currency_id:Int, enter : Float, stop_profit: Double, stop_loss:Double,notes: String,  livedata: MutableLiveData<Trade>?, errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {

        getServergetway().Add_Trades(currency_id,enter,stop_profit,stop_loss,notes)

            .subscribeOn(Schedulers.io())

            .observeOn(AndroidSchedulers.mainThread())

            .map { data -> data }

            .subscribe(
                { books ->
                    livedata?.postValue(books)
                    loadingLivedata.postValue(false)
                },
                {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }

            )

    }



    ////Edit_Trades
    @SuppressLint("CheckResult")
    fun Edit_Trades(
        id:Int,
        currency_id:Int,
        enter: Float,
        stop_profit: Double, stop_loss:Double,
        trade_status: Int,
        notes: String,
        vips:String,
        livedata: MutableLiveData<Trade>?,
        errorLiveData: MutableLiveData<String>, loadingLivedata: MutableLiveData<Boolean>) {

        getServergetway().Edit_Trades(id,currency_id,enter,stop_profit,stop_loss,trade_status,notes,vips)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books)
                    loadingLivedata.postValue(false)
                },
                {
                    errorLiveData.postValue(it.toString());loadingLivedata.postValue(false)
                }
            )
    }

    }


    fun getServergetway () : APIServices
    {
        return ApiClient.client!!.create(APIServices::class.java)
    }


