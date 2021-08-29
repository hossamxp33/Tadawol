package com.example.tadawol.app.presentaion.notification_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tadawol.R
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.NotificationFragmentBinding
import com.example.tadawol.presentaion.notification_fragment.NotificationAdapter


class NotificationFragment  : Fragment() {

    lateinit var MainAdapter: NotificationAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        var view: NotificationFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.notification_fragment, container,false)



            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewModel.GetNotifications()

            viewModel.NotificationLD?.observe(this, Observer {
                MainAdapter = NotificationAdapter(viewModel, context, it)
                view.recyler.layoutManager = LinearLayoutManager(context)
                view.recyler.adapter = MainAdapter;

            })


        return  view.root
    }

}