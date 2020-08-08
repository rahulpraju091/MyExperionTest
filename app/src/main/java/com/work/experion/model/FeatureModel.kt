package com.work.experion.model

import com.google.gson.annotations.SerializedName

data class FeatureModel(
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("imageHref")
    var imageHref: String?
)