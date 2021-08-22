package com.example.tadawol.app

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.presentaion.notification_fragment.NotificationFragment
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.ProfitFragment
import com.example.tadawol.app.presentation.add_edit_trades.AddTradesfragment
import com.example.tadawol.app.presentation.mytrades.Mytrades
import com.example.tadawol.app.presentation.newsfragment.NewsFragment
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment
import com.example.tadawol.app.presentation.upgrade_fragment.Upgrade_Fragment
import com.example.tadawol.app.presentation.user_setting.Setting_Fragment
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private lateinit var viewModel: MainViewModel
    var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().subscribeToTopic("1")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.updateActionBarTitle("الرئيسية")

         PreferenceHelper.getToken()

        binding!!.notification.setOnClickListener {
            val upgrade = NotificationFragment()
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                .replace(R.id.main_frame, upgrade)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
        viewModel.main_title.observe(this, androidx.lifecycle.Observer {
          binding!!.title.text = it
        })
      //  PreferenceHelper.getToken()
        binding!!.context = this
        binding!!.listener = ClickHandler()
        binding!!.btnMenu.setOnClickListener{ v ->
            (this).openCloseNavigationDrawer(v)
        }
        ///////// tool bar and drawerToggle
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.title = ""
        val drawerToggle: ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(this, drawerLayout, toolBar, (R.string.open), (R.string.close)) {
            }


        val   addFragment = NewsFragment()
        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
            .replace(R.id.main_frame, addFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        if (PreferenceHelper.getUserGroupId() == 0) {
            nav_view.menu.get(1).isVisible = false
            nav_view.menu.get(0).isVisible = false

        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {
            R.id.upgrade -> {
         val upgrade = Upgrade_Fragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, upgrade)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
//

            }
            R.id.add_trades -> {
             val   addFragment = AddTradesfragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, addFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.my_trades -> {

                val   setting_fragment = Mytrades()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, setting_fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.more -> {

                val   setting_fragment = Setting_Fragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, setting_fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }


        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun openCloseNavigationDrawer(view: View) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }





}
