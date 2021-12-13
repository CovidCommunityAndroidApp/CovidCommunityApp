package com.example.covidcommunitytracker

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.Fragment
import com.example.covidcommunitytracker.googleAPI.GoogleMapsActivity
import com.example.covidcommunitytracker.googleAPI.GoogleMapsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //permissionRequest()

        val homeFrag = HomeFragment()
        val mapFrag = MapFragment()
        val searchFrag = SearchFragment()
        val infoFrag = InfoFragment()
        val googlePermission = GoogleMapsFragment()

        setCurrentFragment(homeFrag)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFrag)
                //R.id.map -> setCurrentFragment(mapFrag)
                //R.id.search -> setCurrentFragment(searchFrag)
                R.id.info -> setCurrentFragment(infoFrag)
                R.id.googleMaps -> setCurrentFragment(googlePermission)

            }
            true
        }

    }

    private fun permissionRequest() {
        var permissionList = mutableListOf<String>()
        if (!(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissionList.isNotEmpty()) {
            //Log.d("XYZ", "${permissionList.toTypedArray()}")
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 100)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                for (index in grantResults.indices) {
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                        Log.d("XYZ", "Your ${permissions[index]} successfully granted")
                    }
                }
            }
        }
    }


    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}




