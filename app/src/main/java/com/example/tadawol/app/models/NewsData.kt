package com.example.tadawol.app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class NewsData(
    var news: List<New>
)
@Parcelize
data class New(
    var created: String,
    var description: String,
    var id: Int,
    var photo: String,
    var title: String
) : Parcelable {

}