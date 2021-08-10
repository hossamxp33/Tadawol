package com.example.tadawol.app.data_layer



import com.example.tadawol.app.models.Currencies
import com.example.tadawol.app.models.MainTrades
import com.example.tadawol.app.models.Trade
import io.reactivex.Observable
import retrofit2.http.*


interface APIServices {

    @GET("trades.json")
     fun MyTrades(@Query("page") page: Int): Observable<MainTrades>


    @GET("Currencies.json")
    fun Currencies(): Observable<Currencies>



    @FormUrlEncoded
    @POST("trades/add.json")
    abstract fun Add_Trades(
        @Field("currency_id") currency_id : Int,
        @Field("enter") enter: Float,
        @Field("stop_profit") stop_profit: Double,
        @Field("stop_loss") stop_loss: Double,
        @Field("trade_status") trade_status: Int,
        @Field("notes") notes: String,
        @Field("vips") vips: String


    ): Observable<Trade>

}


