package org.yash10019coder.suspectdetectionxml.di

import android.content.Context
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.yash10019coder.suspectdetectionxml.data.Api.ApiService
import org.yash10019coder.suspectdetectionxml.data.AuthInterceptor
import org.yash10019coder.suspectdetectionxml.data.DataStoreUtil
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {
    //    const val BASE_URL = "http://192.168.110.116:5000"
//    const val BASE_URL = "http://172.70.111.186:5000"
    const val BASE_URL = "http://192.168.109.144:5000"


    @Provides
    @Singleton
    fun provideAuthIntecptor(
        @ApplicationContext context: Context,
        dataStoreUtil: DataStoreUtil
    ): AuthInterceptor {
        return AuthInterceptor(dataStoreUtil)
    }

    @Provides
    @Singleton
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil {
        return DataStoreUtil(context)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(
                OkHttpProfilerInterceptor
                    ()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
