package com.work.experion.model

import com.google.gson.annotations.SerializedName

/**
 * Data class.
 *
 * This class handles getter and setter properties.
 */
data class FeatureModel(
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("imageHref")
    var imageHref: String?
) {
    /** @constructor secondary constructor for initialise.
     */
    constructor() : this(null, null, null)
}