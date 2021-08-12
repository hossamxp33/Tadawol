package com.example.tadawol.app

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.Publicusecase.checkUserLogin
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.presentation.ClickHandler
import com.example.tadawol.app.presentation.recommendation_fragment.RecommendationFragment
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import com.example.tadawol.app.presentation.add_edit_trades.Add_Trades_fragment
import com.example.tadawol.app.presentation.login_activity.Login
import java.lang.Exception


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    var title : String ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        viewModel.main_title.observe(this, androidx.lifecycle.Observer {
            title = it

            val value = it ?: return@Observer
            binding.title.text = value

        })
        try {
         binding.title.text = title

        }catch (e : Exception){

        }
        //  PreferenceHelper.getToken()
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
            R.id.add_trades -> {
             val   addFragment = Add_Trades_fragment()
                supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.ttb, 0, 0,0)
                    .replace(R.id.main_frame, addFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.home -> {

//                val homeIntent = Intent(this, BankActitvity::class.java)
//                startActivity(homeIntent)
            }
            R.id.more -> {

                    PreferenceHelper.setToken(null,this)
                    val homeIntent = Intent(this, Login::class.java)
                    Toast.makeText(this, "تم تسجيل خروجك", Toast.LENGTH_SHORT).show()
                    startActivity(homeIntent)

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
