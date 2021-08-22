package com.example.tadawol.app.presentation.viewmodel

import android.app.Activity
import android.graphics.Color
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.tadawol.R
import com.example.tadawol.app.DataRepo.DataRepo
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.models.*
import com.example.tadawol.app.presentation.login_activity.Login
import io.reactivex.disposables.CompositeDisposable
import www.sanju.motiontoast.MotionToast
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}

@BindingAdapter("text_color")
/////// set Stock image statue /////
 fun setTextColor(text: TextView, color: Int?) {
    if (color!! < 0) {

        text.setTextColor(Color.parseColor("#ef1919"))
    } else if (color > 0){

        text.setTextColor(Color.parseColor("#008577"))

    }
        else { // Note the block
            // Display an image on image view from drawable
            text.setTextColor(Color.parseColor("#9C9898"))
        }
    }



@BindingAdapter("datetext")
fun setDatetext(text:TextView,resource: String?) {
try {
    val myFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val dateObj: Date
    dateObj = myFormat.parse(resource)
    val timestamp = dateObj.getTime().toString()//  //Example -> in ms
    val fromServer = SimpleDateFormat("yyyy-MM-dd HH:mm a",Locale("ar"))
    val dateString = fromServer.format(Date(java.lang.Long.parseLong(timestamp)))
    text.text = dateString
}
catch (e :Exception){

}
}



class MainViewModel : ViewModel() {

    var DateRepoCompnay: DataRepo = DataRepo()
     var mCompositeDisposable = CompositeDisposable()

    var errorLivedat: MutableLiveData<String> = MutableLiveData()
    var NotificationLD : MutableLiveData<List<Notification_Data>>? = null

    var loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()
    var LoginResponseLD : MutableLiveData<LoginData>? = null
    var  RegisterResponseLD : MutableLiveData<RegisterModel>? = null
    var TradesResponseLD : MutableLiveData<MainTrades>? = null
    var ProfitResponseLd: MutableLiveData<Profit>? = null
    var AddTradesResponseLD : MutableLiveData<Trade>? = null
    var CurrenciesResponseLD : MutableLiveData<List<Data>>? = null
    var NewsResponseLD : MutableLiveData<List<New>>? = null
    var StockPricesResponseLD : MutableLiveData<List<Price>>? = null
    var     SliderDataResponseLD: MutableLiveData<List<Slider>>? = null
    var     SubscriptionResponseLD: MutableLiveData<List<Data>>? = null

     var main_title = MutableLiveData<String>()


    init {
        errorLivedat= MutableLiveData()
        loadingLivedat= MutableLiveData()
        LoginResponseLD= MutableLiveData()
        TradesResponseLD = MutableLiveData()
        AddTradesResponseLD = MutableLiveData()
        CurrenciesResponseLD = MutableLiveData()
        NewsResponseLD = MutableLiveData()
        StockPricesResponseLD= MutableLiveData()
        main_title = MutableLiveData()
        ProfitResponseLd = MutableLiveData()
        SliderDataResponseLD= MutableLiveData()
        SubscriptionResponseLD= MutableLiveData()
        NotificationLD = MutableLiveData()
    }

///userlogin
fun  Login(username:String,password:String){
    loadingLivedat.postValue(true)

    DateRepoCompnay.userlogin(username,password,LoginResponseLD,errorLivedat,loadingLivedat)


}
    ///userRegister
    fun  userRegister(username:String,mobile:String,password:String){
        loadingLivedat.postValue(true)
            DateRepoCompnay.userRegister(
                username,
                mobile,
                password,
                RegisterResponseLD,
                errorLivedat,
                loadingLivedat
            )

    }
    fun GetNotifications(){
        DateRepoCompnay.GetNotifications(NotificationLD)

    }
    fun  GetTradesData(page:Int){
        loadingLivedat.postValue(true)
        DateRepoCompnay.GetTradesData(page,TradesResponseLD,errorLivedat,loadingLivedat)
    }
    fun  GetTradesDataForUser(page:Int){
        loadingLivedat.postValue(true)
        DateRepoCompnay.GetTradesDataForUser(page,TradesResponseLD,errorLivedat,loadingLivedat)
    }
    /// Get Currencies
    fun  GetCurrenciesData(){
        loadingLivedat.postValue(true)
        DateRepoCompnay.GetCurrenciesData(CurrenciesResponseLD,errorLivedat,loadingLivedat)
    }

    ////GetStockPrice
    fun  GetStockPrice(){
        DateRepoCompnay.GetStockPrice(StockPricesResponseLD,errorLivedat,loadingLivedat)
        loadingLivedat.postValue(true)

    }

    ////////Slider Data
    fun  GetSliderData(){
        DateRepoCompnay.SliderData(SliderDataResponseLD,errorLivedat,loadingLivedat)
        loadingLivedat.postValue(true)

    }
    ///GetSubscriptionsData
    fun  GetSubscriptionsData(){
        DateRepoCompnay.GetSubscriptionsData(SubscriptionResponseLD,errorLivedat,loadingLivedat)
        loadingLivedat.postValue(true)

    }
    /// Get News
    fun  GetNewsData(){
        loadingLivedat.postValue(true)
        DateRepoCompnay.GetMyNewsData(NewsResponseLD,errorLivedat,loadingLivedat)
    }
    /// Add Trades
    fun Add_Trades(currency_id:Int,enter : Float,stop_profit: Double, stop_loss:Double ,notes: String)
    {
        DateRepoCompnay.Add(currency_id,enter,stop_profit,stop_loss,notes,AddTradesResponseLD,errorLivedat,loadingLivedat)

    }


///Edit_Trades
fun Edit_Trades(id:Int,currency_id:Int,enter : Float,stop_profit: Double, stop_loss:Double ,trade_status : Int,notes: String,swtich:String)
{ if (validate(Trade(currency_id,enter,stop_profit,stop_loss, trade_status, notes))) {
        DateRepoCompnay.Edit_Trades(
            id,
            currency_id,
            enter,
            stop_profit,
            stop_loss,
            trade_status,
            notes,

            swtich ,
            AddTradesResponseLD,
            errorLivedat,
            loadingLivedat
        )
    }
}

    private fun validate(data: Trade): Boolean {
        if (data.enter.toString().trim() == "" ||
            data.stop_profit.toString().trim() == ""||
            data.trade_status.toString().trim() == ""||
            data.notes.toString().trim() == ""|| data.vips.toString().trim() == "") {
            errorLivedat.postValue("اكمل البيانات")

            return false
        } else
            return true
    }
    private fun Login_validate(data:  LoginData): Boolean {
        if (data.username.toString().trim() == "" ||
            data.mobile.toString().trim() == "")
        {
            errorLivedat.postValue("اكمل البيانات")

            return false
        } else
            return true
    }

    fun updateActionBarTitle(title: String){
        main_title.postValue(title)
    }

    fun  GetProfitData(){
        loadingLivedat.postValue(true)
        DateRepoCompnay.GetProfitData(ProfitResponseLd,errorLivedat,loadingLivedat)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}