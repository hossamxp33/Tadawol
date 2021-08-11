package com.example.tadawol.app.presentation.add_edit_trades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tadawol.R
import com.example.tadawol.app.models.Data
import com.example.tadawol.app.presentation.viewmodel.MainViewModel
import com.example.tadawol.databinding.AddTradesFragmentBinding
import com.example.tadawol.databinding.RegisterFragmentBinding
import com.example.tadawol.databinding.SignInFragmentBinding

class AddTradesfragment : Fragment(){
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    lateinit  var spinner: Spinner
    var CompanyList:List<Data>? = null
var currency_id : Int ? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:AddTradesFragmentBinding =
            DataBindingUtil.inflate(inflater,
              R.layout.add_trades_fragment, container,false)

        spinner = view.currencySpinner

viewModel.GetCurrenciesData()
        viewModel.CurrenciesResponseLD?.observe(this, Observer {
            spinner.adapter = activity?.applicationContext?.
            let { it1 -> ArrayAdapter(it1, R.layout.spinner, it.map { it.name }) }
            CompanyList  = it
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val arrayadapter =  CompanyList!!.get(position)
               var id = arrayadapter.id
                currency_id = id

            }
        }
        view.btnLogin.setOnClickListener {
//            if (view.etUsername.length() < 4 || view.etPassword.length() < 8 ) {
//                Toast.makeText(context, "يجب ان لا يقل الاسم عن 4 حروف وكلمة المرور 8 حروف ", Toast.LENGTH_SHORT).show()
//            }else{

            val  enterstr : Float = view.enterStr.text.toString().toFloat()
            val  stop_profit : Double = view.stopProfit.text.toString().toDouble()
            val  stop_loss : Double = view.stopLoss.text.toString().toDouble()
            val  trade_status : Int = view.tradeStatus.text.toString().toInt()
            val  note : String = view.note.text.toString()
            val  vip : String = view.vips.text.toString()


            viewModel.Add_Trades(currency_id!!,enterstr,stop_profit,stop_loss,trade_status,note,vip)



        }
   //     }



    return view.root
    }

}