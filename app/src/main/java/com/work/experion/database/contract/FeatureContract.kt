package com.work.experion.database.contract

import android.provider.BaseColumns

/**
 * Contract class.
 *
 * This class handles all fields of the feature table, and table name.
 *
 * @param FEATURE_TABLE is the table name.
 * @param ID is the primary key of the table.
 * @param TITLE is the title field.
 * @param DESCRIPTION is the description field.
 * @param IMAGE_URL is the image url field.
 */
class FeatureContract {
    object Entry : BaseColumns {
        const val FEATURE_TABLE = "feature_table"
        const val ID = "id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE_URL = "image_url"
    }
}
