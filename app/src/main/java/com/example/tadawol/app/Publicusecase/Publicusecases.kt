package com.example.tadawol.app.Publicusecase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.data_layer.ApiClient
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.presentation.login_activity.Login

import okhttp3.MultipartBody
import okhttp3.RequestBody
import www.sanju.motiontoast.MotionToast
import java.io.IOException
import java.lang.Long
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*




@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun changecolor(context: Activity) {
    val window = context.getWindow()
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary))
}

fun showToastBasedOnThrowable(context: Context?,throwable: Throwable?)
{
    var messege = "error occure"
    when (throwable) {
        is IOException -> messege="Something Went Wrong"
        is UnknownHostException ->messege= "No internet connectivity"
        is SocketTimeoutException ->messege= "Slow Internet connectivity"
        else -> throwable?.message
    }

    Toast.makeText(context,messege,Toast.LENGTH_SHORT).show()
}



val Context.isConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
    }

 fun createPartFromString(descriptionString: String?): RequestBody? {
     if (descriptionString!=null)
    return RequestBody.create(
            MultipartBody.FORM, descriptionString)

     else
         return null
}

fun getBitmap(context: Context?): Bitmap {

    val mbitmap = (context?.getResources()?.getDrawable(R.drawable.drawer_icon) as BitmapDrawable).bitmap
    val imageRounded = Bitmap.createBitmap(mbitmap.width, mbitmap.height, mbitmap.config)
    val canvas = Canvas(imageRounded)
    val mpaint = Paint()
    mpaint.isAntiAlias = true
    mpaint.shader = BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    canvas.drawRoundRect(
        RectF(0f, 0f, mbitmap.width.toFloat(), mbitmap.height.toFloat()),
        20f,
        20f,
        mpaint
    )// Round Image Corner 100 100 100 100
    return imageRounded

}


    fun call(context: Context,number: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + number)
          context.startActivity(dialIntent)
    }



fun setupviewPager(viewPager: ViewPager) {
    viewPager.setPageTransformer(true) { view, position ->
        view.translationX = -position * view.width
        if (Math.abs(position) < 0.5) {
            view.visibility = View.VISIBLE
            view.scaleX = 1 - Math.abs(position)
            view.scaleY = 1 - Math.abs(position)
        } else if (Math.abs(position) > 0.5) {
            view.visibility = View.GONE
        }
    }
}

 fun getTime(date: String): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    try {
        val dateObj = sdf.parse(date)
        val timestamp = dateObj.time.toString()//  //Example -> in ms
        val formatter = SimpleDateFormat("hh:mm aa")
        return formatter.format(Date(Long.parseLong(timestamp)))
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return null
}



 fun checkUserLogin(context: Context): Boolean {
     if (!PreferenceHelper.getToken().isNullOrEmpty())
    return true
     else
     {
         Toast.makeText(context,"يجب تسجيل الدخول اولا ",Toast.LENGTH_SHORT).show()
         return  false
     }
}
/// open whats app
fun openWhatsApp(smsNumber: String, context: Context) {

    val sendIntent = Intent(Intent.ACTION_SEND)
    sendIntent.type = "text/plain"
    sendIntent.putExtra(Intent.EXTRA_TEXT,"أرسل استفسارك...")
    sendIntent.putExtra("jid", "$smsNumber@s.whatsapp.net") //phone number without "+" prefix
    sendIntent.setPackage("com.whatsapp")
    if (sendIntent.resolveActivity(context.packageManager) == null) {
        Toast.makeText(context, "Error/n", Toast.LENGTH_SHORT).show()
        return
    }
    context.startActivity(sendIntent)
}

//open  Facebook
fun openFacebook(context: Context) {

    val openURL = Intent(android.content.Intent.ACTION_VIEW)
    openURL.data = Uri.parse("https://www.facebook.com/IRAQKMCO")
    context.startActivity(openURL)
}

/// open Url
fun openUrl(context: Context) {

    val openURL = Intent(android.content.Intent.ACTION_VIEW)
     openURL.data = Uri.parse("https://www.al-fateh-iq.com/")
    context.startActivity(openURL)
}
///////// ERROR_MotionToast
fun ERROR_MotionToast(massage : String,context: Context){
    MotionToast.createColorToast(context as Login,
        "Error",
        massage,
        MotionToast.TOAST_ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, R.font.helvetica_regular))
}

///TOAST_SUCCESS_MotionToast
fun SUCCESS_MotionToast(massage : String,context: Context){
    MotionToast.createColorToast(context as Login,
        "Hurray success 😍",
        massage,
        MotionToast.TOAST_SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, R.font.helvetica_regular))
}
/// for main activity+

fun ERROR_MotionToast_main(massage : String,context: Context){
    MotionToast.createColorToast(context as MainActivity,
        "Error",
        massage,
        MotionToast.TOAST_ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, R.font.helvetica_regular))
}

//////////////
fun loudImage(context: Context, imag: ImageView, url: String?) {
    Glide.with(context).applyDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.noimg)
            .error(R.drawable.noimg)).load(url).into(imag)
}