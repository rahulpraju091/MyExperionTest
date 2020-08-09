package com.work.experion.model

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("title")
    var title: String?,
    @SerializedName("rows")
    var rows: ArrayList<FeatureModel?>?
) {
    constructor() : this(null, null)
}