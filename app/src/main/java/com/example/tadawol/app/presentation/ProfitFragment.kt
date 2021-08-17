package com.example.tadawol.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.helper.PreferenceHelper
import com.example.tadawol.app.presentation.aachartcorelib.aachartcreator.AAChartModel
import com.example.tadawol.app.presentation.aachartcorelib.aachartcreator.AASeriesElement
import com.example.tadawol.app.presentation.aachartcorelib.aachartenum.AAChartType
import com.example.tadawol.app.presentation.aachartcorelib.aaoptionsmodel.AAStyle
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.ProfitFragmentBinding

class ProfitFragment : Fragment() {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: ProfitFragmentBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.profit_fragment, container,false)

        viewModel.updateActionBarTitle("الارباح")
        //attaches LinearLayoutManager with RecyclerView
        viewModel.GetProfitData()
viewModel.ProfitResponseLd?.observe(this,Observer { it ->

//view.profittext.text =  "   مجموع الارباح" +  it.profittotal!!.first().profit!!.toString()

var data = arrayListOf<Float>()
   val monthes = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )
    val values = arrayOf(
        0.0,    0.0,   0.0,    0.0,    0.0,    0.0,
        0.0,    0.0,    0.0,    0.0,    0.0,    0.0
    )
    it.profit!!.forEachIndexed { index, element ->
        var i = index
        values.set(element.month!!.toInt() - 1,element.profit!!.toDouble())
    }
    val aaChartModel: AAChartModel = AAChartModel()
        .chartType(AAChartType.Line)
        .title("الارباح")
        .axesTextColor("#FFFFFF")
        .titleStyle(AAStyle.style("#FFFFFF", 20f))
        .backgroundColor("#070D2D")

        .categories(
            monthes
        )
        .dataLabelsEnabled(false)
        .yAxisGridLineWidth(0f)
        .series(
            arrayOf<AASeriesElement>(
                AASeriesElement()
                    .name("Profit")

                    .data(
                        values
                    )

                    .color("rgba(51, 216, 197,1)")

            )
        )

    view.aachartview.aa_drawChartWithChartModel(aaChartModel)


})
        return view.root
    }
}