package com.example.tadawol.app.data_layer



import com.example.tadawol.app.models.MainTrades
import com.example.tadawol.app.models.Trade
import io.reactivex.Observable
import retrofit2.http.*


interface APIServices {

    @GET("trades.json")
     fun MyTrades(): Observable<MainTrades>



}


