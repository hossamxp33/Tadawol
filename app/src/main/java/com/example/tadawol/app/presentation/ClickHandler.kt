package com.example.tadawol.app.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.Publicusecase.checkUserLogin
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.models.New
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.add_edit_trades.Edit_Trades_fragment
import com.example.tadawol.app.presentation.contact_us.Contact_Us_Fragment
import com.example.tadawol.app.presentation.login_activity.Login
import com.example.tadawol.app.presentation.newsfragment.Details_News_Fragment
import com.example.tadawol.app.presentation.newsfragment.NewsFragment
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment
import com.example.tadawol.app.presentation.stock_price.StockPriceFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.recipe_placeholder_item.*

class ClickHandler {

    /// Switch To Recommends
    fun SwitchToRecommends(context: Context) {
        SwitchFun(context as MainActivity, RecommendationFragment())
        SetDefaultColor(context)
        SelectedItemColor(context.binding!!.deal)
    }
    /// Switch To Contact Us

    fun SwitchToContactUs(context: Context) {
        SwitchFun(context, Contact_Us_Fragment())
    }

    /// Switch To Login
    fun SwitchToLogin(context: Context) {

        if (checkUserLogin(context)) {
            PreferenceHelper.setToken(null, (context))
            val homeIntent = Intent(context, Login::class.java)
            context.startActivity(homeIntent)
            Toast.makeText((context as MainActivity), "تم تسجيل خروجك", Toast.LENGTH_SHORT).show()

        }
    }


    /////  Switch To News
    fun SwitchToNews(context: Context) {

        SwitchFun(context as MainActivity, NewsFragment())
        SetDefaultColor(context)
        SelectedItemColor(context.binding!!.main)

    }

    /////  Switch To News Details
    fun SwitchToNewsDetails(context: Context, data: New) {

        val bundle = Bundle()
        bundle.putParcelable("news_data", data)
        val news_fragment = Details_News_Fragment()
        news_fragment.arguments = bundle
        SwitchFun(context as MainActivity,news_fragment)
        SetDefaultColor(context)
        SelectedItemColor(context.binding!!.main)

    }

    //////  Switch To Stock Price Fragment
    fun SwitchToStockPriceFragment(context: Context) {

        SwitchFun(context as MainActivity, StockPriceFragment())
        SetDefaultColor(context)
        SelectedItemColor(context.binding!!.company)

    }

    ////////Switch To Edit Fragment
    fun SwitchToEditFragment(context: Context, data: Trade) {

        if (data.close_date != "1") {
            val bundle = Bundle()

            val edit_fragment = Edit_Trades_fragment()

            bundle.putInt("id", data.id!!)
            bundle.putParcelable("data", data)

            edit_fragment.arguments = bundle

            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, edit_fragment).addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        }
    }
/// make call
fun DoCall(con:Context, phone: String?)
{
    val intent =  Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "07721499299"));
    con.startActivity(intent);
}



    ///// Main Switch Fun
    fun SwitchFun(context: Context, fragment: Fragment) {

        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    ///// Selected Item Color
    fun SelectedItemColor(img: AppCompatImageView) {
        img.animate()?.apply {
            translationX(-30f)
        }
        img.setBackgroundResource(R.drawable.under_line)

    }

    fun SetDefaultColor(context: Context) {
        (context as MainActivity).binding?.chart!!.setBackgroundResource(R.color.float_transparent)
        (context).binding?.main!!.setBackgroundResource(R.color.float_transparent)
        (context).binding?.company!!.setBackgroundResource(R.color.float_transparent)
        (context).binding?.deal!!.setBackgroundResource(R.color.float_transparent)
    }
}