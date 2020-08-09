package com.work.experion.model

import com.google.gson.annotations.SerializedName

/**
 * Data class.
 *
 * This class handles getter and setter properties.
 */
data class CityModel(
    @SerializedName("title")
    var title: String?,
    @SerializedName("rows")
    var rows: ArrayList<FeatureModel?>?
) {
    /** @constructor secondary constructor for initialise.
     */
    constructor() : this(null, null)
}