package com.example.tadawol.app.presentation.recommendation_fragment

import android.os.Bundle
import android.os.Handler
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
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.RecommendationsFragmentBinding
import kotlinx.android.synthetic.main.recommendations_fragment.*


class RecommendationFragment : Fragment(){
    lateinit var viewModel: MainViewModel
    lateinit var MainAdapter: Recommendations_Adapter
    //handler instance
    var handler: Handler = Handler()
    //list for holding data
    lateinit var list : ArrayList<String>
    private var isLoading: Boolean = false
    lateinit var layoutManager : LinearLayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:RecommendationsFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.recommendations_fragment, container,false)

        viewModel =   ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        viewModel.updateActionBarTitle("التوصيات")

        layoutManager = LinearLayoutManager(context)
        //attaches LinearLayoutManager with RecyclerView
        view.recyler.layoutManager = layoutManager
        viewModel.GetTradesData()
        viewModel.TradesResponseLD?.observe(this , Observer { it ->
            MainAdapter = Recommendations_Adapter( viewModel,context, it)
            view.recyler.layoutManager = LinearLayoutManager(context)
            view.recyler.adapter = MainAdapter;
            list = ArrayList()
            //attaches scrollListener with RecyclerView
            load()
            addScrollerListener()
            stoploading()
        })

    return view.root
    }
    override fun onResume() {
        super.onResume()
         shimmer_view_container.startShimmerAnimation()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmerAnimation()
        super.onPause()
    }
    fun stoploading() {
        shimmer_view_container?.setVisibility(View.GONE)
        shimmer_view_container?.stopShimmerAnimation()

    }

    private fun addScrollerListener()
    {
        //attaches scrollListener with RecyclerView
        recyler.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading)
                {
                    //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
                    ////It checks, fully visible view is the last one.
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1)
                    {
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }
    private fun loadMore()
    {
        //notify adapter using Handler.post() or RecyclerView.post()
        handler.post(Runnable
        {
            list.add("load")
            MainAdapter.notifyItemInserted(list.size - 1)
        })
        handler.postDelayed(Runnable {
            //removes "load".
            list.removeAt(list.size - 1)
            var listSize = list.size
            MainAdapter.notifyItemRemoved(listSize)
            //sets next limit
            var nextLimit = listSize + 2
            for(i in listSize until nextLimit)
            {
                list.add("Item No $i")
            }
            MainAdapter.notifyDataSetChanged()
            isLoading = false
        },2500)
    }
    private fun load()
    {
        for(i in 0..2)
        {
            list.add("Item No: $i")
        }
    }
}