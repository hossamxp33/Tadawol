package com.example.tadawol.app.presentation.upgrade_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter

import com.example.tadawol.R
import com.example.tadawol.app.Publicusecase.loudImage
import com.example.tadawol.app.models.Slider
import kotlinx.android.synthetic.main.viewpagerslide.view.*





class SliderAdapter(activity: FragmentActivity, sliders: List<Slider>) : PagerAdapter() {

    private val context: Context
    private val slidersData: List<Slider> = sliders

    init {
        this.context = activity
    }

    override fun getCount(): Int {
        return  slidersData.size
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.viewpagerslide, container, false)
        loudImage(context,view.im_slider,slidersData[position].photo)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
//    override fun getPageWidth(position: Int): Float {
//        return 0.93f
//    }

}
