package dev.alfianh.firsttestsuitmedia.networks

import dev.alfianh.firsttestsuitmedia.models.Guest
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by alfianh on 8/9/17.
 */
interface ApiService {
    @GET("api/people")
    fun getPeople(): Call<List<Guest>>
}