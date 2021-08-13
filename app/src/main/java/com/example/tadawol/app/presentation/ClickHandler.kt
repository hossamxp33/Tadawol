package com.example.tadawol.app.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentTransaction
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.Publicusecase.checkUserLogin
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.add_edit_trades.Edit_Trades_fragment
import com.example.tadawol.app.presentation.login_activity.Login
import com.example.tadawol.app.presentation.newsfragment.NewsFragment
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment
import com.example.tadawol.app.presentation.stock_price.StockPriceFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.recipe_placeholder_item.*

class ClickHandler {


    fun SwitchToRecommends( context: Context) {

        val recommendation_fragment = RecommendationFragment()
        ( context as MainActivity). supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, recommendation_fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        SetDefaultColor(context)
        SelectedItemColor(context.binding!!.deal)
    }
    fun SwitchToLogin( context: Context) {

                if (checkUserLogin  (context)) {
                    PreferenceHelper.setToken(null,  (context))
                    val homeIntent = Intent(context, Login::class.java)
                    context.startActivity(homeIntent)
                    Toast.makeText(  ( context as MainActivity), "تم تسجيل خروجك", Toast.LENGTH_SHORT).show()

                }
                }


    fun SwitchToNews( context: Context) {

        val news_fragment = NewsFragment()
        ( context as MainActivity). supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, news_fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        SetDefaultColor(context)
        SelectedItemColor(context.binding!!.main)

    }
    fun SwitchToStockPriceFragment( context: Context) {

        val stockPrices_fragment = StockPriceFragment()
        ( context as MainActivity). supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, stockPrices_fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        SetDefaultColor(context)
        SelectedItemColor(context.binding!!.company)
    }

    fun SwitchToEditFragment( context: Context, data :Trade) {
        val bundle = Bundle()

        val edit_fragment = Edit_Trades_fragment()

        bundle.putInt("id",data.id!!)
        bundle.putParcelable("data",data)

        edit_fragment.arguments = bundle

        ( context as MainActivity). supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, edit_fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }





    /////
    fun SelectedItemColor( img: AppCompatImageView){
        img.animate()?.apply {
            translationX(-30f)
        }
        img.setBackgroundResource(R.drawable.under_line)

    }

    fun SetDefaultColor( context: Context){
        ( context as MainActivity).binding?.chart!!.setBackgroundResource(R.color.float_transparent)
        ( context as MainActivity).binding?.main!!.setBackgroundResource(R.color.float_transparent)
        ( context as MainActivity).binding?.company!!.setBackgroundResource(R.color.float_transparent)
        ( context as MainActivity).binding?.deal!!.setBackgroundResource(R.color.float_transparent)
    }
}