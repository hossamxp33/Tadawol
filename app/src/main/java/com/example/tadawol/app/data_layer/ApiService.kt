package com.example.tadawol.app.data_layer


import com.example.tadawol.app.models.*
import io.reactivex.Observable
import retrofit2.http.*


interface APIServices {

    ///////// Login API
    @FormUrlEncoded
    @POST("api/users/token.json")
    abstract fun userlogin(
        @Field("mobile") username: String,
        @Field("password") password: String
    ): Observable<LoginModel>

    ///////// Login API
    @FormUrlEncoded
    @POST("api/users/add.json")
    abstract fun userRegister(
        @Field("username") username: String,
        @Field("mobile") mobile: String,
        @Field("password") mobpasswordile: String

    ): Observable<RegisterModel>


    ///// Get Trades
    @GET("trades.json")
    fun MyTrades(@Query("page") page: Int): Observable<MainTrades>

    @GET("trades/getTradesByUserId/{id}.json")
    fun MyTradesForUser(
        @Path("id") id: Int, @Query("page") page: Int,
    ): Observable<MainTrades>


    @GET("Trades/getprofitforuser.json")
    fun MyProfit(): Observable<Profit>

    ///// Get News
    @GET("news.json")
    fun MyNews(): Observable<NewsData>

    ////// Currencies
    @GET("Currencies.json")
    fun Currencies(): Observable<Currencies>

    ////// Currencies
    @GET("Trades/price.json")
    fun GetStockPrice(): Observable<StockPrices>
    // Notifications
    @GET("notifications.json")/*{company_id}*/
    fun GetNotifications():
            Observable<Notification>

    //// Add
    @FormUrlEncoded
    @POST("trades/add.json")
    abstract fun Add_Trades(
        @Field("currency_id") currency_id: Int,
        @Field("enter") enter: Float,
        @Field("stop_profit") stop_profit: Double,
        @Field("stop_loss") stop_loss: Double,
        @Field("notes") notes: String,
        @Field("user_id") user_id: String,


        ): Observable<Trade>


    /// Edit
    @FormUrlEncoded
    @POST("trades/edit/{id}.json")
    abstract fun Edit_Trades(
        @Path("id") id: Int,
        @Field("currency_id") currency_id: Int,
        @Field("enter") enter: Float,
        @Field("stop_profit") stop_profit: Double,
        @Field("stop_loss") stop_loss: Double,
        @Field("trade_status") trade_status: Int,
        @Field("notes") notes: String,
        @Field("close_date") close_date: String


    ): Observable<Trade>


    //// Slider
    @POST("sliders.json")/*{company_id}*/
    fun SliderData():
            Observable<SliderData>

    //////subscriptions.json
    @POST("subscriptions.json")/*{company_id}*/
    fun SubscriptionsData():
            Observable<Subscriptions>
}


