package com.work.experion.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import com.work.experion.database.contract.CityContract
import com.work.experion.database.contract.FeatureContract

/**
 * Database class.
 *
 * This class handles all the DB actions like creating and updating tables.
 *
 * @param DATABASE_NAME is the name of the database.
 * @param DATABASE_VERSION is the version number of the database
 * @param CREATE_CITY_TABLE is the variable used for create city table
 * @param CREATE_FEATURE_TABLE is the variable used for create Feature table
 */
class DataBaseHelper(private var context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {
        private var instance: DataBaseHelper? = null
        fun getHelper(context: Context): DataBaseHelper? {
            if (instance == null) {
                instance = DataBaseHelper(context.applicationContext)
            }
            return instance
        }

        const val DATABASE_NAME = "ExperionTest.db"
        const val DATABASE_VERSION = 1
        const val CREATE_CITY_TABLE = "CREATE TABLE " +
                CityContract.Entry.CITY_TABLE + "(" +
                CityContract.Entry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CityContract.Entry.TITLE + " TEXT )"
        const val CREATE_FEATURE_TABLE = "CREATE TABLE " +
                FeatureContract.Entry.FEATURE_TABLE + "(" +
                FeatureContract.Entry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FeatureContract.Entry.TITLE + " TEXT, " +
                FeatureContract.Entry.DESCRIPTION + " TEXT, " +
                FeatureContract.Entry.IMAGE_URL + " TEXT )"
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db?.setForeignKeyConstraintsEnabled(true)
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_CITY_TABLE)
        db?.execSQL(CREATE_FEATURE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


}