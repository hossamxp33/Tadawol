package com.example.tadawol.app.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.add_edit_trades.Edit_Trades_fragment
import com.example.tadawol.app.presentation.newsfragment.NewsFragment
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment
import com.example.tadawol.app.presentation.stock_price.StockPriceFragment

class ClickHandler {


    fun SwitchToRecommends( context: Context) {

        val recommendation_fragment = RecommendationFragment()
        ( context as MainActivity). supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, recommendation_fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }
    fun SwitchToNews( context: Context) {

        val news_fragment = NewsFragment()
        ( context as MainActivity). supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, news_fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


    }
    fun SwitchToStockPriceFragment( context: Context) {

        val stockPrices_fragment = StockPriceFragment()
        ( context as MainActivity). supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, stockPrices_fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


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


}