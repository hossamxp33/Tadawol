package com.example.tadawol.app.presentation.add_edit_trades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.models.Data
import com.example.tadawol.app.models.Trade
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.AddTradesFragmentBinding
import com.example.tadawol.databinding.EditTradesFragmentBinding
import com.example.tadawol.databinding.RegisterFragmentBinding
import com.example.tadawol.databinding.SignInFragmentBinding
import www.sanju.motiontoast.MotionToast
import java.lang.Exception

class Edit_Trades_fragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    lateinit  var spinner: Spinner
    var CompanyList:List<Data>? = null
    var trade_Data:Trade? = null
var currency_id : Int ? = null
    var trade_id : Int ? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:EditTradesFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.edit_trades_fragment, container,false)
        trade_id = arguments?.getInt("id")
        trade_Data= arguments?.getParcelable("data")
        spinner = view.currencySpinner

        viewModel.GetCurrenciesData()

     ///// Get Currency Data with Spinner ///////
        viewModel.CurrenciesResponseLD?.observe(this, Observer {
            spinner.adapter = activity?.applicationContext?.
            let { it1 -> ArrayAdapter(it1, R.layout.spinner, it.map { it.name }) }
            CompanyList  = it
        })
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               val arrayadapter =  CompanyList!!.get(position)
               var id = arrayadapter.id
                currency_id = id
            }
        }

        ///////// Set Data for inputs
        view.enterStr.setText(trade_Data?.enter.toString())
        view.stopProfit.setText(trade_Data?.stop_profit.toString())
        view.stopLoss.setText(trade_Data?.stop_loss.toString())
        view.tradeStatus.setText(trade_Data?.trade_status.toString())
        view.note.setText(trade_Data?.notes)
        view.vips.setText(trade_Data?.vips)


//// Edit Button .. send data
        view.btnLogin.setOnClickListener {
      try {
        val  enterstr : Float = view.enterStr.text.toString().toFloat()
        val  stop_profit : Double = view.stopProfit.text.toString().toDouble()
        val  stop_loss : Double = view.stopLoss.text.toString().toDouble()
        val  trade_status : Int = view.tradeStatus.text.toString().toInt()
        val  note : String = view.note.text.toString()
        val  vip : String = view.vips.text.toString()

    viewModel.Edit_Trades(trade_id!!,currency_id!!,enterstr,stop_profit,stop_loss,trade_status,note,vip)
    }catch (e : Exception)
    {
    Toast.makeText(context, "أكمل البيانات", Toast.LENGTH_SHORT).show()
     }
        }

        viewModel.AddTradesResponseLD?.observe(this, Observer {
            if (it.success)
            {

            }
            else
            {
                MotionToast.createColorToast(activity!!, "Hurray success 😍", "أكمل البيانات",
                    MotionToast.TOAST_ERROR,
                    MotionToast.GRAVITY_TOP,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(activity!!, R.font.helvetica_regular))            }
        })

        viewModel.errorLivedat.observe(this,
            Observer {
                MotionToast.createColorToast(activity!!,
                    "Hurray success 😍",
                    it,
                    MotionToast.TOAST_ERROR,
                    MotionToast.GRAVITY_TOP,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(activity!!, R.font.helvetica_regular))
            })



    return view.root
    }

}