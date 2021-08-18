package com.example.tadawol.app.presentation.upgrade_fragment

import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide.init
import com.example.tadawol.R
import com.example.tadawol.app.models.New
import com.example.tadawol.app.presentation.recommendation_fragment.Recommendations_Adapter
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NewsDetailsBinding
import com.example.tadawol.databinding.UpgradeFragmentBinding
import kotlinx.android.synthetic.main.recommendations_fragment.*
import kotlinx.android.synthetic.main.upgrade_fragment.*
import kotlinx.android.synthetic.main.upgrade_fragment.recyler
import java.util.*


open class Upgrade_Fragment:Fragment() {
    val viewModel : MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    var MainAdapter : Subscriptions_Adapter ? = null
    private var currentPage = 0
    private var NUM_PAGES = 0
    var data : New? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: UpgradeFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.upgrade_fragment, container, false)
        viewModel.updateActionBarTitle("ترقية")
        viewModel.GetSliderData()

        viewModel.GetSubscriptionsData()

        viewModel.SubscriptionResponseLD?.observe(this, Observer {
            recyler.adapter
            MainAdapter =    it?.let {Subscriptions_Adapter(viewModel,requireActivity(), it) }!!
            recyler.layoutManager = LinearLayoutManager(context)
            recyler.adapter = MainAdapter

        })
        viewModel.SliderDataResponseLD?.observe(this , Observer {
            //  pager!!.setRotation(-90f)
            view.pager.offscreenPageLimit = 3
            //  pager!!.pageMargin = 20
            view.pager   .clipChildren = false
            view.pager   .clipToPadding = false
            //   pager!!.setPadding(100, 0, 50, 0)

            view.pager.adapter = it?.let { it1 -> SliderAdapter(activity!!, it1) }

            indicator.setViewPager(view.pager)
            pager!!.setPageTransformer(true, HorizontalFlipTransformation())

            it?.size?.let { it1 -> init(it1) }
            stoploading()
        })

        return view.root
    }
    override fun onResume() {
        super.onResume()

        shimmer_view.startShimmerAnimation()
    }
    override fun onPause() {
        shimmer_view.stopShimmerAnimation()
        super.onPause()
    }
    fun stoploading() {
        shimmer_view?.setVisibility(View.GONE)
        shimmer_view?.stopShimmerAnimation()

    }
    private fun init(size: Int) {
        val density = getResources().getDisplayMetrics().density
        indicator.setRadius(4 * density)

        NUM_PAGES = size
        val handler = Handler()
        val Update:Runnable = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            pager?.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 10000)
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position


            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })
    }

}