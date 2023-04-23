package org.yash10019coder.suspectdetectionxml.data.Api

import org.yash10019coder.suspectdetectionxml.data.model.response.GetSuspectResponseModel
import org.yash10019coder.suspectdetectionxml.data.model.SuspectModel
import org.yash10019coder.suspectdetectionxml.data.model.response.AddSuspectResponseModel
import org.yash10019coder.suspectdetectionxml.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/suspects")
    suspend fun getSuspects(): Response<List<GetSuspectResponseModel>>

    @GET("/suspects/{id}")
    suspend fun getSuspectById(@Path("id") id: Long): Response<GetSuspectResponseModel>

    @POST("/add/suspect")
    suspend fun addSuspect(@Body suspectModel: SuspectModel): Response<AddSuspectResponseModel>

    @FormUrlEncoded
    @POST("/login")
    suspend fun login(@Field("username") username: String,@Field("password") password: String): Response<LoginResponse>
}
