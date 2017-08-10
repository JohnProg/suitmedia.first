package dev.alfianh.firsttestsuitmedia.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dev.alfianh.firsttestsuitmedia.R
import dev.alfianh.firsttestsuitmedia.activities.MenuActivity
import dev.alfianh.firsttestsuitmedia.adapters.EventAdapter
import dev.alfianh.firsttestsuitmedia.models.Event

/**
 * Created by alfianh on 8/10/17.
 */
class EventFragment : Fragment(), EventAdapter.Listener {

    private var rvContent: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_event, container, false)
        rvContent = view.findViewById(R.id.rvContent) as RecyclerView
        rvContent?.layoutManager = LinearLayoutManager(activity)
        rvContent?.hasFixedSize()
        rvContent?.adapter = EventAdapter(activity, getLists(), this)
        return view
    }

    fun getLists(): ArrayList<Event> {
        var lists = ArrayList<Event>()
        lists.add(Event("Pagelaran Seni", R.drawable.suitmedia, resources.getString(R.string.lorem), LatLng(-6.9802872, 107.5033356)))
        lists.add(Event("Seminar Android", R.drawable.suitmedia, resources.getString(R.string.lorem), LatLng(-6.962992, 107.631137)))
        lists.add(Event("Seminar iOS", R.drawable.suitmedia, resources.getString(R.string.lorem), LatLng(-6.9803079, 107.6338574)))
        lists.add(Event("Bandung Developer Day", R.drawable.suitmedia, resources.getString(R.string.lorem), LatLng(-6.962992, 107.631137)))
        return lists;
    }

    override fun onClick(event: Event) {
        var resultIntent = Intent();
        resultIntent.putExtra("event", event.name)
        activity.setResult(MenuActivity.EVENT_RESULT_CODE, resultIntent);
        activity.finish();
    }
}