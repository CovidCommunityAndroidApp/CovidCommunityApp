package com.example.covidcommunitytracker.googleAPI

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.covidcommunitytracker.R
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.lang.Object

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task

class GoogleMapsFragment : Fragment() , OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient //used to give most recent location
    private val LOCATION_PERMISSION_REQUEST = 1

    private lateinit var currentLocation: Location //used to find users last known location
    //Location is
    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this.requireContext())
    }
    private fun updateMapLocation(location:LatLng)
    {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,7f))
    }

    private fun addMarkerAtLocation(location:LatLng, title:String)
    {
        map.addMarker(MarkerOptions().title(title).position(location))
    }
    private fun getLocationAccess() {
        if (ContextCompat.checkSelfPermission(this.requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        }
        else
            ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
    }
    private val callback = OnMapReadyCallback { googleMap ->
       /* val myPlace = LatLng(40.73, -73.99) // New York
        googleMap.addMarker(MarkerOptions().position(myPlace).title("My Favorite City"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(myPlace))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 12.0f))

        */
        onMapReady(googleMap)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_google_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.requireContext())
        super.onViewCreated(view, savedInstanceState)
        permissionRequest()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback) // when view is initialized call back is automatically called

        }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this.requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this.requireContext() as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        map.isMyLocationEnabled = true

         fusedLocationClient.lastLocation.addOnSuccessListener(this.requireActivity()) { location ->
            // Get last known location.
            if (location != null) {
                currentLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
               // map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                map.addMarker(MarkerOptions().position(currentLatLng).title("My Current Location"))
                map.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12.0f))
            }
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.isMyLocationEnabled
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
        setUpMap()

    }

    //checks if user already has permission and if not a permission request is sent
    private fun permissionRequest() {
         var permissionList = mutableListOf<String>()
         if (!(ActivityCompat.checkSelfPermission(
                 this.requireContext(),
                 Manifest.permission.ACCESS_COARSE_LOCATION
             ) == PackageManager.PERMISSION_GRANTED)
         ) {
             permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
         }
         if (!(ActivityCompat.checkSelfPermission(
                 this.requireContext(),
                 Manifest.permission.ACCESS_FINE_LOCATION
             ) == PackageManager.PERMISSION_GRANTED)
         ) {
             permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
         }
         if (permissionList.isNotEmpty()) {
             //Log.d("XYZ", "${permissionList.toTypedArray()}")
             ActivityCompat.requestPermissions(this.context as Activity, permissionList.toTypedArray(), 100)
             
         }
     }



     private fun getLastLocation()
     {
         if (ActivityCompat.checkSelfPermission(
                 this.requireContext(),
                 Manifest.permission.ACCESS_FINE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                 this.requireContext(),
                 Manifest.permission.ACCESS_COARSE_LOCATION
             ) != PackageManager.PERMISSION_GRANTED
         ) {
             return
         }
         fusedLocationProviderClient.lastLocation.addOnSuccessListener {
             location: Location? ->location.let {
                 val userLoc = location?.let{
                     val userLocation = LatLng(location.latitude,location.longitude)
                     updateMapLocation(userLocation)
                     addMarkerAtLocation(userLocation, "You")
                     Log.d("works", "works")
                 }
         }
         }
     }

    override fun onMarkerClick(p0: Marker?) = false


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }


}








