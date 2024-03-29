package com.example.covidcommunitytracker

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY = "m-_QNwXvOhlW8i2ebeJfodtYE1gcGzUv0TXbWg5TyWEyqOfj66WG4g9x3N6KlDCq-gcGPTIBNI3yjqtlSWeUmdaVODkI8PP8fEaibjYTJsmF_1v-TUrFK2Tq6oWvYXYx"
/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //retrofit
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        //recycler view
        val businesses = mutableListOf<YelpBusiness>()
        val adapter = CustomAdapter(this.requireContext(),businesses)

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this.context)
        //ON BUTTON PRESS
        button_find_vacc.setOnClickListener {
            val ZIP = etZIP.text.toString()
            if (ZIP.isNotEmpty() && ZIP.length == 5) {
                businesses.clear()
                adapter.notifyDataSetChanged()
                val yelpService = retrofit.create(YelpService::class.java)
                //SEARCHING BUSINESSES
                yelpService.searchBusiness("Bearer $API_KEY", "Covid Vaccine", ZIP)
                    .enqueue(object : Callback<YelpSearchResult> {
                        override fun onResponse(
                            call: Call<YelpSearchResult>,
                            response: Response<YelpSearchResult>
                        ) {
                            Log.i(TAG, "onResponse $response")
                            val body = response.body()
                            if (body != null) {
                                businesses.addAll(body.business)
                                adapter.notifyDataSetChanged()
                            }
                        }

                        override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                            Log.i(TAG, "onFailure $t")
                        }
                    })
            }
            else{
                Toast.makeText(this.context,"Please Enter A Valid ZIP",Toast.LENGTH_SHORT).show()
            }
        }
        Testing_Site.setOnClickListener {
            val ZIP = etZIP.text.toString()
            if (ZIP.isNotEmpty() && ZIP.length == 5) {
                businesses.clear()
                adapter.notifyDataSetChanged()
                val yelpService = retrofit.create(YelpService::class.java)
                //SEARCHING BUSINESSES
                yelpService.searchBusiness("Bearer $API_KEY", "Covid Testing", ZIP)
                    .enqueue(object : Callback<YelpSearchResult> {
                        override fun onResponse(
                            call: Call<YelpSearchResult>,
                            response: Response<YelpSearchResult>
                        ) {
                            Log.i(TAG, "onResponse $response")
                            val body = response.body()
                            if (body != null) {
                                businesses.addAll(body.business)
                                adapter.notifyDataSetChanged()
                            }
                        }

                        override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                            Log.i(TAG, "onFailure $t")
                        }
                    })
            }
            else{
                Toast.makeText(this.context,"Please Enter A Valid ZIP",Toast.LENGTH_SHORT).show()
            }

        }
        // Instead of view.findViewById(R.id.hello) as TextView
        /*recyclerview?.layoutManager = LinearLayoutManager(this.context)

        val data = ArrayList<YelpBusiness>()

        //creating cards for recycler view and adding to data arrray
        for(i in 1..20){
            //data.add(ItemsViewModel(R.drawable.ic_search,"Item " + i))
        }

        val adapter = CustomAdapter(data)

        recyclerview.adapter = adapter*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}