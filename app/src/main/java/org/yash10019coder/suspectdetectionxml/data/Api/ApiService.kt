package org.yash10019coder.suspectdetectionxml.data.Api

import org.yash10019coder.suspectdetectionxml.data.model.LoginModel
import org.yash10019coder.suspectdetectionxml.data.model.SuspectModel
import org.yash10019coder.suspectdetectionxml.data.model.response.AddSuspectResponseModel
import org.yash10019coder.suspectdetectionxml.data.model.response.GetSuspectResponseModel
import org.yash10019coder.suspectdetectionxml.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/suspects")
    suspend fun getSuspects(@Header("auth_token") authToken: String): Response<List<GetSuspectResponseModel>>

    @GET("/suspects/{id}")
    suspend fun getSuspectById(
        @Header("auth_token") authToken: String,
        @Path("id") id: Long
    ): Response<GetSuspectResponseModel>

    @POST("/add/suspect")
    suspend fun addSuspect(
        @Header("auth_token") authToken: String,
        @Body suspectModel: SuspectModel
    ): Response<AddSuspectResponseModel>

    @POST("/login")
    suspend fun login(@Body loginModel: LoginModel): Response<LoginResponse>
}
