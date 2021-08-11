package com.example.tadawol.app.models

data class RegisterModel(
    var `data`: RegisterData,
    var success: Boolean
)

data class RegisterData(
    var email: Any,
    var groupid: Any,
    var mobile: Any,
    var roomid: Any,
    var token: String,
    var userid: Any,
    var username: Any
)