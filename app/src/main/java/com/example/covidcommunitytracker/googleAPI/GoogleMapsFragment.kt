package com.example.covidcommunitytracker.googleAPI


import androidx.fragment.app.Fragment

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.covidcommunitytracker.R
import com.google.android.gms.maps.model.MarkerOptions

import java.lang.Object

class GoogleMapsFragment : Fragment() {
    private const val PERMISSION_CODE_REQUEST_LOCATION = 1

    /**
     * This demo shows how GMS Location can be used to check for changes to the users location.  The
     * "My Location" button uses GMS Location to set the blue dot representing the users location.
     * Permission for [Manifest.permission.ACCESS_FINE_LOCATION] is requested at run
     * time. If the permission has not been granted, the Activity is finished with an error message.
     */
    class GoogleMapsFragment : AppCompatActivity(), GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {
        override fun onMyLocationButtonClick(): Boolean {
            TODO("Not yet implemented")
        }
    }


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
        /**
         * Flag indicating whether a requested permission has been denied after returning in
         * [.onRequestPermissionsResult].
         */
       /* private var permissionDenied = false
        private lateinit var map: GoogleMap
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_google_maps)
            val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(this)
        }

        override fun onMapReady(googleMap: GoogleMap) {
            map = googleMap
            googleMap.setOnMyLocationButtonClickListener(this)
            googleMap.setOnMyLocationClickListener(this)
            enableMyLocation()
        }

        /**
         * Enables the My Location layer if the fine location permission has been granted.
         */
        @SuppressLint("MissingPermission")
        private fun enableMyLocation() {
            if (!::map.isInitialized) return
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                // Permission to access the location is missing. Show rationale and request permission
                requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true
                )
            }
        }

        override fun onMyLocationButtonClick(): Boolean {
            Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show()
            // Return false so that we don't consume the event and the default behavior still occurs
            // (the camera animates to the user's current position).
            return false
        }

        override fun onMyLocationClick(location: Location) {
            Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG).show()
        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
                return
            }
            if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Enable the my location layer if the permission has been granted.
                enableMyLocation()
            } else {
                // Permission was denied. Display an error message
                // Display the missing permission error dialog when the fragments resume.
                permissionDenied = true
            }
        }

        override fun onResumeFragments() {
            super.onResumeFragments()
            if (permissionDenied) {
                // Permission was not granted, display error dialog.
                showMissingPermissionError()
                permissionDenied = false
            }
        }

        /**
         * Displays a dialog with error message explaining that the location permission is missing.
         */
        private fun showMissingPermissionError() {
            newInstance(true).show(supportFragmentManager, "dialog")


        }

        companion object {
            /**
             * Request code for location permission request.
             *
             * @see .onRequestPermissionsResult
             */
            private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_google_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

        */
}