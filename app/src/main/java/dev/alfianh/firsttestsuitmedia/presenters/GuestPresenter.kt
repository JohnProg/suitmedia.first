package dev.alfianh.firsttestsuitmedia.presenters

import android.util.Log
import dev.alfianh.firsttestsuitmedia.models.Guest
import dev.alfianh.firsttestsuitmedia.networks.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by alfianh on 8/9/17.
 */

class GuestPresenter : BasePresenter<GuestPresenter.GuestView>() {

    fun getPeople() {
        mvpView!!.onShowLoading()
        val getPeopleCall: Call<List<Guest>> = RestClient.instance.getService().getPeople()
        getPeopleCall.enqueue(object : Callback<List<Guest>> {
            override fun onResponse(call: Call<List<Guest>>, response: Response<List<Guest>>) {
                mvpView!!.onDismissLoading()
                if (response.isSuccessful) {
                    mvpView!!.onSuccess(response.body())
                } else {
                    mvpView!!.onFailed("Gagal Mendapatkan data !")
                }
            }

            override fun onFailure(call: Call<List<Guest>>, t: Throwable) {
                mvpView!!.onFailed("Gagal Mendapatkan data !")
                Log.e("Suitmedia", t.localizedMessage)
                mvpView!!.onDismissLoading()
            }
        })
    }

    interface GuestView : MvpView {
        fun onSuccess(guests: List<Guest>)
    }
}
