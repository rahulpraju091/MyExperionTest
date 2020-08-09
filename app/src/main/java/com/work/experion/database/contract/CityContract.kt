package com.work.experion.database.contract

import android.provider.BaseColumns

/**
 * Contract class.
 *
 * This class handles all fields of the city table, and table name.
 *
 * @param CITY_TABLE is the table name.
 * @param ID is the primary key of the table.
 * @param TITLE is the title field.
 */
class CityContract {
    object Entry : BaseColumns {
        const val CITY_TABLE = "city_table"
        const val ID = "id"
        const val TITLE = "title"
    }
}
