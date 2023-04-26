package org.yash10019coder.suspectdetectionxml.data.model.response


import com.google.gson.annotations.SerializedName

data class GetSuspectResponseModel(
    @SerializedName("age")
    var age: Int,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("_id")
    var id: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("info")
    var info: List<Info>,
    @SerializedName("name")
    var name: String,
    @SerializedName("username")
    var username: String
) {
    data class Info(
        @SerializedName("location")
        var location: String,
        @SerializedName("remark")
        var remark: String,
        @SerializedName("time")
        var time: Int
    )
}
