package dev.alfianh.firsttestsuitmedia.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import dev.alfianh.firsttestsuitmedia.R
import dev.alfianh.firsttestsuitmedia.adapters.GuestAdapter
import dev.alfianh.firsttestsuitmedia.models.Guest
import dev.alfianh.firsttestsuitmedia.presenters.GuestPresenter
import kotlinx.android.synthetic.main.activity_guest.*

/**
 * Created by alfianh on 8/9/17.
 */
class GuestActivity : AppCompatActivity(), GuestPresenter.GuestView, GuestAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    var adapter: GuestAdapter? = null
    var dialog: ProgressDialog? = null
    var data: ArrayList<Guest> = ArrayList<Guest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        rvContent.layoutManager = GridLayoutManager(this, 2)
        rvContent.hasFixedSize()
        adapter = GuestAdapter(this, data, this)
        rvContent.adapter = adapter
        dialog = ProgressDialog(this)
        dialog?.setMessage("Loading...")
        swipe.setOnRefreshListener(this)
        getPeople()
    }

    companion object {
        fun start(context: Context) {
            (context as Activity).startActivityForResult(Intent(context, GuestActivity::class.java), MenuActivity.ACTIVITY_REQUEST_CODE)
        }
    }

    fun getPeople() {
        var presenter = GuestPresenter()
        presenter.attachView(this)
        presenter.getPeople()
    }

    override fun onClick(guest: Guest) {
        var resultIntent = Intent();
        resultIntent.putExtra("guest", guest.name)
        resultIntent.putExtra("birthdate", guest.birthdate)
        setResult(MenuActivity.GUEST_RESULT_CODE, resultIntent);
        finish();
    }

    override fun onShowLoading() {
        swipe.isRefreshing = true
    }

    override fun onDismissLoading() {
        swipe.isRefreshing = false
    }

    override fun onFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
    }

    override fun onSuccess(guests: List<Guest>) {
        data.clear()
        data.addAll(guests)
        for (guest in guests) {
            Toast.makeText(this, guest.name, Toast.LENGTH_SHORT)
        }
        adapter!!.notifyDataSetChanged()
    }

    override fun onRefresh() {
        getPeople()
    }
}