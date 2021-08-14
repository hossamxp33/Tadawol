package com.example.tadawol.app.presentation.login_activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.provider.Settings.Secure;

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.example.tadawol.R
import com.example.tadawol.app.MainActivity
import com.example.tadawol.app.Publicusecase.checkUserLogin
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.presentation.login_activity.tabs_fragments.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity()  {
 var android_id : String ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        PreferenceHelper(this)
        if (checkUserLogin(this))
            startActivity(Intent(this  , MainActivity::class.java))

      //////
        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);


        // Tabs Customization
        tab_layout.setSelectedTabIndicatorColor(getColor(R.color.white))
        tab_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.signinpurple))
        tab_layout.tabTextColors = ContextCompat.getColorStateList(this, R.color.text_blue)

        // Set different Text Color for Tabs for when are selected or not
        //tab_layout.setTabTextColors(R.color.normalTabTextColor, R.color.selectedTabTextColor)

        // Number Of Tabs
        val numberOfTabs = 2

        // Set Tabs in the center
        //tab_layout.tabGravity = TabLayout.GRAVITY_CENTER

        // Show all Tabs in screen
        tab_layout.tabMode = TabLayout.MODE_FIXED

        // Scroll to see all Tabs
        //tab_layout.tabMode = TabLayout.MODE_SCROLLABLE

        // Set Tab icons next to the text, instead above the text
        tab_layout.isInlineLabel = true

        // Set the ViewPager Adapter
        val adapter =
            TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
             tabs_viewpager.adapter = adapter

        // Enable Swipe
        tabs_viewpager.isUserInputEnabled = true

        // ...
        // Link the TabLayout and the ViewPager2 together and Set Text & Icons
        TabLayoutMediator(tab_layout, tabs_viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "حساب جديد"
                    //   tab.setIcon(R.drawable.ic_music)
                }
                1 -> {
                    tab.text = "تسجيل دخول"
                    //    tab.setIcon(R.drawable.ic_movie)

                }

            }
            // Change color of the icons
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()

    }





}