package com.example.openremote.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.openremote.R
import com.example.openremote.databinding.ActivityHomeBinding
import com.example.openremote.request.RequestActivity
import com.example.openremote.util.Const.REQUEST_PERMISSIONS_REQUEST_CODE
import com.google.android.gms.maps.model.Marker
import org.osmdroid.views.MapView
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.IconOverlay.ANCHOR_CENTER
import org.osmdroid.views.overlay.Marker.ANCHOR_CENTER

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        map = binding.map
        map.setTileSource(TileSourceFactory.MAPNIK)
        mapConfig()
    }

    private fun mapConfig() {
        val currentLat = intent.getDoubleExtra("lat", 0.1)
        val currentLong = intent.getDoubleExtra("long", 0.1)
        Log.e("Lat", currentLat.toString())
        val mapController = map.controller
        mapController.setZoom(15)
        val startPoint = GeoPoint(16.4637, 107.5909)
        mapController.setCenter(startPoint)

        //marker - location của thiết bị hiện tại
        val myLocation = org.osmdroid.views.overlay.Marker(map)
        myLocation.position = startPoint
        myLocation.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_location_on_24)
        myLocation.title = "Vị trí thiết bị của tôi"
        map.overlays.add(myLocation)
        map.invalidate()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}