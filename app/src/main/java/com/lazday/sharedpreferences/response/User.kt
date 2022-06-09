package com.lazday.sharedpreferences.response

import com.google.gson.annotations.SerializedName


data class User (

    @SerializedName("id_user"  ) var idUser   : String? = null,
    @SerializedName("nama"     ) var nama     : String? = null,
    @SerializedName("jen_kel"  ) var jenKel   : String? = null,
    @SerializedName("no_telp"  ) var noTelp   : String? = null,
    @SerializedName("foto"     ) var foto     : String? = null,
    @SerializedName("username" ) var username : String? = null,
    @SerializedName("password" ) var password : String? = null

)