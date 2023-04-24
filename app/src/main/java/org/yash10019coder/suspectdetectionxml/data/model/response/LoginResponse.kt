package org.yash10019coder.suspectdetectionxml.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val status: Int,
    val message: String,
    @SerializedName("access_token")
    val accessToken: String
)
