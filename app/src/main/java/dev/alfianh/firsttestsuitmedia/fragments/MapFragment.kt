package dev.alfianh.firsttestsuitmedia.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dev.alfianh.firsttestsuitmedia.R
import android.widget.LinearLayout
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.ViewParent
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import dev.alfianh.firsttestsuitmedia.models.Event
import org.w3c.dom.Text
import android.support.v4.view.ViewPager.OnPageChangeListener
import com.google.android.gms.maps.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdate
import android.opengl.ETC1.getHeight


/**
 * Created by alfianh on 8/10/17.
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private var adapter: CustomPagerAdapter? = null
    private var slide: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_map, container, false)
        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        adapter = CustomPagerAdapter(activity, getLists())
        slide = view.findViewById(R.id.slide) as ViewPager
        slide?.adapter = adapter
        slide?.offscreenPageLimit
        slide?.clipToPadding = true
        slide?.setPageMargin(24);
        slide?.setPadding(48, 8, 48, 8);
        slide?.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                val location = CameraUpdateFactory.newLatLngZoom(
                        getLists()[position].latLng, 15f)
                mMap?.animateCamera(location)
            }
        });
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

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        getLists().forEachIndexed { index, event ->
            val latLng = event.latLng
            mMap?.addMarker(MarkerOptions().position(latLng).title(event.name))
        }
        val location = CameraUpdateFactory.newLatLngZoom(
                getLists()[0].latLng, 15f)
        mMap?.animateCamera(location)

    }

    internal inner class CustomPagerAdapter(var mContext: Context, datas: ArrayList<Event>) : PagerAdapter() {
        var mLayoutInflater: LayoutInflater
        var datas: List<Event> = datas

        init {
            mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        override fun getCount(): Int {
            return datas.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as RelativeLayout
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false)

            val imageView = itemView.findViewById(R.id.imageView) as ImageView
            val tvEventName = itemView.findViewById(R.id.tvEventName) as TextView
            imageView.setImageResource(datas[position].image)
            tvEventName.text = datas[position].name

            container.addView(itemView)

            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as RelativeLayout)
        }
    }
}