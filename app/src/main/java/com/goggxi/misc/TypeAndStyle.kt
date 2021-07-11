package com.goggxi.misc

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.goggxi.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import java.lang.Exception

class TypeAndStyle {

    fun setMapStyle(context: Context, googleMap: GoogleMap) {
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context , R.raw.style
                )
            )
            if (!success) {
                Log.d("Maps", "setMapStyle: filed to add style")
            }
        } catch (e: Exception) {
            Log.d("Maps", "setMapStyle: $e")
        }
    }

    fun setMapTypes(item: MenuItem, mMap: GoogleMap) {
        when(item.itemId) {
            R.id.normal_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.hybrid_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.satellite_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            R.id.terrain_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            R.id.none_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
    }

}