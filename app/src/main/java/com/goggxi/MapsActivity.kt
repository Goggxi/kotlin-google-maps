package com.goggxi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.goggxi.databinding.ActivityMapsBinding
import com.goggxi.misc.CameraAndViewport
import com.goggxi.misc.TypeAndStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val coffeeCrime = LatLng(-5.1412245, 119.478825)
        val nipahMall = LatLng(-5.139552628653523, 119.4500048571976)
        mMap.addMarker(MarkerOptions().position(coffeeCrime).title("Coffee Crime"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coffeeCrime, 15f ))
        // camera zoom
//        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.coffeeCrime))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(coffeeCrime))
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isCompassEnabled = true
        }
        typeAndStyle.setMapStyle( this ,mMap)

        lifecycleScope.launch {
            delay(4000)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(nipahMall))
            mMap.addMarker(MarkerOptions().position(nipahMall).title("Nipah Mall"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapTypes(item, mMap)
        return true
    }
}