package com.example.covidcommunitytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.Fragment
import com.example.covidcommunitytracker.googleAPI.GoogleMapsActivity
import com.example.covidcommunitytracker.googleAPI.GoogleMapsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFrag = HomeFragment()
        val mapFrag = MapFragment()
        val searchFrag = SearchFragment()
        val infoFrag = InfoFragment()
        val googlemapActivity = GoogleMapsActivity()

        setCurrentFragment(homeFrag)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(homeFrag)
                R.id.map->setCurrentFragment(mapFrag)
                R.id.search->setCurrentFragment(searchFrag)
                R.id.info->setCurrentFragment(infoFrag)
                //R.id.googleMaps->setCurrentFragment(googlemapActivity)
               // setContentView(R.layout.activity_google_maps)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}