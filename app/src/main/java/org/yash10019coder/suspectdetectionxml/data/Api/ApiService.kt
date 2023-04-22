package org.yash10019coder.suspectdetectionxml.data.Api

import org.yash10019coder.suspectdetectionxml.data.model.GetSuspectResponseModel
import org.yash10019coder.suspectdetectionxml.data.model.SuspectModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/suspects")
    suspend fun getSuspects(): Response<List<GetSuspectResponseModel>>

    @GET("/suspects/{id}")
    suspend fun getSuspectById(@Path("id") id: Long): Response<GetSuspectResponseModel>

    @POST("/add/suspect")
    suspend fun addSuspect(@Body suspectModel: SuspectModel): Response<SuspectModel>

    @POST("/login")
    suspend fun login(username: String, password: String): Response<SuspectModel>
}
