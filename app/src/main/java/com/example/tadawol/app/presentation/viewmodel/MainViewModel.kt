package com.example.tadawol.app.presentation.viewmodel

import android.graphics.Color
import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.tadawol.app.DataRepo.DataRepo
import com.example.tadawol.app.models.Trade
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
@BindingAdapter("app:imageResourcee")
fun setImageResourcee(imageView: AppCompatImageView, resource: String?) {
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




@BindingAdapter("formattedText")
fun formattedText(view: View, text: String?) {
    (view as AppCompatTextView).text = if (text == null || text.isEmpty()) {
        ""
    } else {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
        val startDate = simpleDateFormat.parse(text)
        DateUtils.getRelativeTimeSpanString(startDate?.time ?: 0).toString()


    }
}

class MainViewModel : ViewModel() {

    var DateRepoCompnay: DataRepo = DataRepo()
     var mCompositeDisposable = CompositeDisposable()
    var errorLivedat: MutableLiveData<Throwable> = MutableLiveData()

    var loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()
    var TradesResponseLD : MutableLiveData<List<Trade>>? = null
     var main_title = MutableLiveData<String>()


    init {

        TradesResponseLD = MutableLiveData()
        errorLivedat= MutableLiveData()
        loadingLivedat= MutableLiveData()
        main_title = MutableLiveData()

    }



    fun  GetTradesData(page:Int){
        loadingLivedat.postValue(true)
        DateRepoCompnay.GetTradesData(page,TradesResponseLD,errorLivedat,loadingLivedat)
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