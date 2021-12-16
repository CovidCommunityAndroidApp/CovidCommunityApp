package com.example.covidcommunitytracker.googleAPI

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.covidcommunitytracker.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import java.util.jar.Manifest
import java.lang.Object

private const val PERMISSION_CODE_REQUEST_LOCATION = 1
private lateinit var fusedLocationClient: FusedLocationProviderClient
private lateinit var map: GoogleMap
 abstract class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }
/*
    //method below used to request standard permission request dialog
    private fun requestLocationPermission()
    {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_CODE_REQUEST_LOCATION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode)
        {
            PERMISSION_CODE_REQUEST_LOCATION -> if(
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ){
                getLastLocation()
            }
            else
            {
                requestPermissionWithRationaleIfNeeded()
            }
        }
    }

    private fun showPermissionRationale(positiveAction: () -> Unit)
    {
        AlertDialog.Builder(this)
            .setTitle("Location Permission")
            .setMessage("This app will not work with out knowing your current location")
            .setPositiveButton("OK")
            { _,_ -> positiveAction()}
            .create()
            .show()
        }
    private fun requestPermissionWithRationaleIfNeeded() = if (
        ActivityCompat.shouldShowRequestPermissionRationale(
            this, android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    ){
        showPermissionRationale { requestLocationPermission() }
    }
    else{
        requestLocationPermission()
    }

    // method is used to check if the user has already previously gave permission
    private fun hasLocationPermission() =
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED

    override fun onMapReady(googleMap: GoogleMap) {
        var mMap = googleMap

        if (hasLocationPermission())
        {
            getLastLocation()
        }

        else
        {
            requestPermissionWithRationaleIfNeeded()
        }
    }

}

    private fun getLastLocation(){
        Log.d("GoogleMapsActivity", "getLastLocation() called.")
    }

 */
}
