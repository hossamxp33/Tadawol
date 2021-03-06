package com.example.tadawol.app

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.updateActionBarTitle("الرئيسية")


        viewModel.main_title.observe(this, androidx.lifecycle.Observer {
          binding.title.text = it
        })

        binding.context = this
        binding.listener = ClickHandler()
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

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {
            R.id.recommendations -> {
         val homeFragment = RecommendationFragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
//

            }
            R.id.reports -> {
//                reportsFragment = ReportsFragment()
//                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
//                    .replace(R.id.main_frame, reportsFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
            }
            R.id.home -> {

//                val homeIntent = Intent(this, BankActitvity::class.java)
//                startActivity(homeIntent)
            }
            R.id.more -> {
//                if (checkUserLogin(this)) {
//                    PreferenceHelper.setAuthId("0",this)
//                    Toast.makeText(this, "تم تسجيل خروجك", Toast.LENGTH_SHORT).show()
//
//                    val homeIntent = Intent(this, LoginActivity::class.java)
//                    startActivity(homeIntent)
//                }
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
