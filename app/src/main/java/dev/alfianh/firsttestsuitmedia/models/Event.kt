package dev.alfianh.firsttestsuitmedia.models

import com.google.android.gms.maps.model.LatLng

/**
 * Created by alfianh on 8/9/17.
 */
data class Event(var name: String,
                 var image: Int,
                 var desc: String,
                 var latLng: LatLng) {
}