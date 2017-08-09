package dev.alfianh.firsttestsuitmedia.networks

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by alfianh on 8/9/17.
 */
class RestClient {
    private val service: ApiService

    companion object {
        @JvmField
        var instance = RestClient()

        fun getInstance(): RestClient {
            if (instance == null)
                instance = RestClient()

            return instance
        }
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://dry-sierra-6832.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttp())
                .build()

        service = retrofit.create(ApiService::class.java)

    }

    fun getOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("Suitmedia", message) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)

        builder.connectTimeout(120, TimeUnit.SECONDS)
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.writeTimeout(120, TimeUnit.SECONDS)


        return builder.build()
    }

    fun getService(): ApiService {
        return service
    }
}