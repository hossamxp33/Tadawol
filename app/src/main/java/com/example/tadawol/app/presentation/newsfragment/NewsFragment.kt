package com.example.tadawol.app.presentation.newsfragment

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
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.Publicusecase.showToastBasedOnThrowable
import com.example.tadawol.app.models.New
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NewsFragmentBinding
import kotlinx.android.synthetic.main.recommendations_fragment.*
import java.util.*
import kotlin.collections.ArrayList


class NewsFragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    lateinit var MainAdapter: News_Adapter

    lateinit var list : ArrayList<New>
     var data : New ? = null


    internal var page = 1



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view:NewsFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.news_fragment, container,false)
           page = 1

        viewModel.updateActionBarTitle("اراء المحللين")
        viewModel.GetNewsData()

        view.context = context as MainActivity
        //attaches LinearLayoutManager with RecyclerView

//////////////////// Paging
//        view.recyler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val lastVisibleItem =
//                    (Objects.requireNonNull(recyclerView.layoutManager) as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
//                if (lastVisibleItem == MainAdapter.getItemCount() -1) {
//                    page++
//                    viewModel.GetNewsData()
//
//                }
//            }
//        })
//

    return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewModel.GetNewsData()


        viewModel.loadingLivedat.observe(this,
            Observer { loading -> progress.setVisibility(if (loading!!) View.VISIBLE else View.GONE) })

        viewModel.NewsResponseLD?.observe(this , Observer { it ->
            if (page == 1) {
                list = ArrayList(it)
                if (list.size>0) {
                    MainAdapter =    it?.let { it1 -> News_Adapter(viewModel,activity!!, list) }!!
                    recyler.layoutManager = LinearLayoutManager(context)
                    recyler.adapter = MainAdapter;
                    stoploading()

                }
            } else{
                list.addAll(it)
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

    override fun onPause() {
        shimmer_view_container.stopShimmerAnimation()
        super.onPause()
    }
    fun stoploading() {
        shimmer_view_container?.setVisibility(View.GONE)
        shimmer_view_container?.stopShimmerAnimation()

    }



}