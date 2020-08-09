package com.work.experion.database.contract

import android.provider.BaseColumns

class CityContract {
    object Entry : BaseColumns {
        const val CITY_TABLE = "city_table"
        const val ID = "id"
        const val TITLE = "title"
    }
}
