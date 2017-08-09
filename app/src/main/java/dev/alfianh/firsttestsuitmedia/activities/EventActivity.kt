package dev.alfianh.firsttestsuitmedia.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import dev.alfianh.firsttestsuitmedia.R
import dev.alfianh.firsttestsuitmedia.adapters.EventAdapter
import dev.alfianh.firsttestsuitmedia.models.Event
import kotlinx.android.synthetic.main.activity_event.*

/**
 * Created by alfianh on 8/9/17.
 */
class EventActivity : AppCompatActivity(), EventAdapter.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        rvContent.layoutManager = LinearLayoutManager(this)
        rvContent.hasFixedSize()
        rvContent.adapter = EventAdapter(this, getLists(), this)
    }

    companion object {
        fun start(context: Context) {
            (context as Activity).startActivityForResult(Intent(context, EventActivity::class.java), MenuActivity.ACTIVITY_REQUEST_CODE)
        }
    }

    fun getLists(): ArrayList<Event> {
        var lists = ArrayList<Event>()
        lists.add(Event("Pagelaran Seni", R.drawable.suitmedia))
        lists.add(Event("Seminar Android", R.drawable.suitmedia))
        lists.add(Event("Seminar iOS", R.drawable.suitmedia))
        lists.add(Event("Bandung Developer Day", R.drawable.suitmedia))
        return lists;
    }

    override fun onClick(event: Event) {
        var resultIntent = Intent();
        resultIntent.putExtra("event", event.name)
        setResult(MenuActivity.EVENT_RESULT_CODE, resultIntent);
        finish();
    }
}