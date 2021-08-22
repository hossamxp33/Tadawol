package com.example.tadawol.app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Trade(

    var currency_id: Int ?=null,
    var enter: Float ?=null,
    var stop_profit: Double ?=null,
    var stop_loss: Double ?=null,
    var trade_status: Int ?=null,
    var vips: String ?=null,
    var notes: String ?=null,
    var id: Int ?=null,
    var created: String ?=null,
    var currency: Currency ?=null,
    var modified: String ?=null,
    var success: Boolean  = false,
    var user_id: Int?  = null,

    var close_date: String ?=null

)   : Parcelable {

}