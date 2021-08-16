package com.example.tadawol.app.presentation.recommendation_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tadawol.R
import com.example.tadawol.app.Publicusecase.showToastBasedOnThrowable
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RecommendationsFragmentBinding
import kotlinx.android.synthetic.main.recommendations_fragment.*
import java.util.*
import kotlin.collections.ArrayList


open class RecommendationFragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    lateinit var MainAdapter: Recommendations_Adapter

    lateinit var list : ArrayList<Trade>
    internal var page = 1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: RecommendationsFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.recommendations_fragment, container,false)
           page = 1
         getdata(view)
        viewModel.updateActionBarTitle("التوصيات")
        //attaches LinearLayoutManager with RecyclerView





    return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorLivedat.observe(this, Observer {
            if ( page == 1 )
                showToastBasedOnThrowable(context,Throwable())
        })


        viewModel.loadingLivedat.observe(this,
            Observer { loading -> progress.setVisibility(if (loading!!) View.VISIBLE else View.GONE) })

        viewModel.TradesResponseLD?.observe(this , Observer { it ->
            if (page == 1) {
                list = ArrayList(it.trades)
                if (list.size>0) {
                    MainAdapter =    it?.let { it1 -> Recommendations_Adapter(viewModel,requireActivity(), list) }!!
                    recyler.layoutManager = LinearLayoutManager(context)
                    recyler.adapter = MainAdapter;
                    stoploading()

                }
            } else{
                list.addAll(it.trades)
                MainAdapter.notifyDataSetChanged()
                recyler.scrollToPosition(MainAdapter.getItemCount() - 9)
                stoploading()

            }
            //attaches scrollListener with RecyclerView
        })


    }
    override fun onResume() {
        super.onResume()
         shimmer_view_container.startShimmerAnimation()
    }

    open fun getdata(view: RecommendationsFragmentBinding) {
        viewModel.GetTradesData(page)
        view. recyler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItem =
                    (Objects.requireNonNull(recyclerView.layoutManager) as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (lastVisibleItem == MainAdapter.getItemCount() -1) {
                    page++
                    viewModel.GetTradesData(page)

                }
            }
        })
    }
    override fun onPause() {
        shimmer_view_container.stopShimmerAnimation()
        super.onPause()
    }
    fun stoploading() {
        shimmer_view_container?.setVisibility(View.GONE)
        shimmer_view_container?.stopShimmerAnimation()

    }



}