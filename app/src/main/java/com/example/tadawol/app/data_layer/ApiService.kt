package com.example.tadawol.app.data_layer



import com.example.tadawol.app.models.*
import io.reactivex.Observable
import retrofit2.http.*


interface APIServices {

    ///////// Login API
    @FormUrlEncoded
    @POST("api/users/token.json")
    abstract fun userlogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginModel>

    ///////// Login API
    @FormUrlEncoded
    @POST("api/users/add.json")
    abstract fun userRegister(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<RegisterModel>

    @GET("trades.json")
     fun MyTrades(@Query("page") page: Int): Observable<MainTrades>


    @GET("Currencies.json")
    fun Currencies(): Observable<Currencies>


//// Add
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


    /// Edit
    @FormUrlEncoded
    @POST("trades/edit/{id}.json")
    abstract fun Edit_Trades(
        @Path ("id") id : Int,
        @Field("currency_id") currency_id : Int,
        @Field("enter") enter: Float,
        @Field("stop_profit") stop_profit: Double,
        @Field("stop_loss") stop_loss: Double,
        @Field("trade_status") trade_status: Int,
        @Field("notes") notes: String,
        @Field("vips") vips: String


    ): Observable<Trade>
}


