package com.work.experion.database.contract

import android.provider.BaseColumns

class FeatureContract {
    object Entry : BaseColumns {
        const val FEATURE_TABLE = "feature_table"
        const val ID = "id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE_URL = "image_url"
    }
}
