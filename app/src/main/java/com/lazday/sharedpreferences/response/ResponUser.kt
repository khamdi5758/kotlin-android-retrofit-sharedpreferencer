package com.lazday.sharedpreferences.response

class ResponUser (
    val success:Boolean = true,
    val message: String? = null,
    var user: List<User>
)