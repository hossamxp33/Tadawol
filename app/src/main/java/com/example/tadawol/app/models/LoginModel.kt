package com.example.tadawol.app.models

data class LoginModel(
    var `data`: LoginData,
    var success: Boolean
)

data class LoginData(
    var email: String,
    var groupid: Int,
    var mobile: String,
    var roomid: Any,
    var token: String,
    var userid: Int,
    var username: String
)