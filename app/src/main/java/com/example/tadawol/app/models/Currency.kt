package com.example.tadawol.app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
    var created: String,
    var id: Int,
    var modified: String,
    var name: String,
    var price_buy: Int,
    var price_sell: Int
)   : Parcelable {

}