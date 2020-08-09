package com.work.experion.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase

open class DbDao() {

    var database: SQLiteDatabase? = null
    private var dbHelper: DataBaseHelper? = null

    constructor(context: Context) : this() {
        if (dbHelper == null) {
            dbHelper = DataBaseHelper.getHelper(context)
        }
        database = dbHelper?.writableDatabase
    }

}