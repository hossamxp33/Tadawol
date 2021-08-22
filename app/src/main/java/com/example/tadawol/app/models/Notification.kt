package com.example.tadawol.app.models


data class Notification(
    var data: List<Notification_Data>,
    var success: Boolean
)

data class Notification_Data(
    var  id : String ?= null,
    var  text : String ?= null,
    var  photo : String ?= null,

    var  created : String ?= null

)