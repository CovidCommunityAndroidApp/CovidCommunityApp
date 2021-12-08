package com.example.covidcommunitytracker

import com.google.gson.annotations.SerializedName

data class YelpSearchResult (
    @SerializedName("total") val total: Int,
    @SerializedName("businesses") val business: List<YelpBusiness>

    )
data class YelpBusiness(
    @SerializedName("name") val name: String,
    val location: YelpLocation,
    @SerializedName("image_url") val imageUrl: String
)

data class YelpLocation(
    @SerializedName("address1") val address: String,
)