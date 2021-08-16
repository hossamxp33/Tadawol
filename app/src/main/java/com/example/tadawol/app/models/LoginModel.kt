package com.example.tadawol.app.models

data class LoginModel(
    var `data`: LoginData,
    var success: Boolean
)

data class LoginData(
    var email: String?=null,
    var mobile: String?=null,

    var groupid: Int?=null,
    var roomid: Any?=null,
    var token: String?=null,
    var userid: Int?=null,
    var username: String?=null
)