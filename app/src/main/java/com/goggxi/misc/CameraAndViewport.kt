package com.goggxi.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

class CameraAndViewport {

    val coffeeCrime : CameraPosition = CameraPosition.builder()
        .target(LatLng(-5.1412245, 119.478825))
        .zoom(18f)
        .bearing(100f)
        .tilt(45f)
        .build()

}