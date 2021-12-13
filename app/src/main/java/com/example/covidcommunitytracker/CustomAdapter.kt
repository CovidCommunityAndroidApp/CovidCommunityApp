package com.example.covidcommunitytracker


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_view.view.*

class CustomAdapter(val context: Context, private val mList: List<YelpBusiness>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val business = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.bind(business)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        fun bind(business: YelpBusiness){
            itemView.textViewName.text = business.name
            itemView.tvLocation.text = business.location.address
            Glide.with(itemView).load(business.imageUrl).into(itemView.IVbusiness)
        }
    }
}