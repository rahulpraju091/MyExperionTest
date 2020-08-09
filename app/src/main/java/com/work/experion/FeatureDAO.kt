package com.work.experion

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.work.experion.database.DbDao
import com.work.experion.database.contract.CityContract
import com.work.experion.database.contract.FeatureContract
import com.work.experion.model.CityModel
import com.work.experion.model.FeatureModel

/**
 * DAO class.
 *
 * This class handles all feature related DB operations.
 * @param context is the Context value of calling class. Used for initialize DB
 */
class FeatureDAO(context: Context) : DbDao(context) {

    /** DB insert function. Its used for insert city data into city table.
     * @return insert long value if the insertion is success.
     */
    fun insertCityDetailsToDB(response: CityModel): Long {
        var insert: Long = 0
        val values = ContentValues()
        values.put(CityContract.Entry.TITLE, response.title)
        insert = database!!.insert(CityContract.Entry.CITY_TABLE, null, values)
        return insert
    }

    /** DB insert function. Its used for insert feature list into feature table. */
    fun insertFeatureDetails(rows: ArrayList<FeatureModel?>?) {
        for (i in 0..rows!!.size.minus(1)) {
            val featureValues = ContentValues().also {
                it.put(FeatureContract.Entry.TITLE, rows[i]?.title)
                it.put(
                    FeatureContract.Entry.DESCRIPTION,
                    rows[i]?.description
                )
                it.put(FeatureContract.Entry.IMAGE_URL, rows[i]?.imageHref)
            }
            database!!.insert(FeatureContract.Entry.FEATURE_TABLE, null, featureValues)
        }
    }

    /** DB truncate function.
     * Its used for truncate all existing data from tables. */
    fun truncateAllTables() {
        val sqlCity = "DELETE FROM " + CityContract.Entry.CITY_TABLE
        val sqlFeature = "DELETE FROM " + FeatureContract.Entry.FEATURE_TABLE
        database!!.execSQL(sqlCity)
        database!!.execSQL(sqlFeature)
    }

    /** DB read function. Its used for get city details. */
    @SuppressLint("Recycle")
    fun getCityDetails(): CityModel {
        val cityDetails = CityModel()
        val cursor: Cursor =
            database!!.query(CityContract.Entry.CITY_TABLE, null, null, null, null, null, null)
        if (cursor.moveToNext()) {
            cityDetails.title = cursor.getString(cursor.getColumnIndex(CityContract.Entry.TITLE))
        }
        return cityDetails
    }

    /** DB read function. Its used for get feature details. */
    @SuppressLint("Recycle")
    fun getFeatureList(): ArrayList<FeatureModel?>? {
        val featureList: ArrayList<FeatureModel?>? = arrayListOf()
        val cursor: Cursor =
            database!!.query(
                FeatureContract.Entry.FEATURE_TABLE,
                null,
                null,
                null,
                null,
                null,
                null
            )
        while (cursor.moveToNext()) {
            val featureItem = FeatureModel()
            featureItem.title = cursor.getString(cursor.getColumnIndex(FeatureContract.Entry.TITLE))
            featureItem.description =
                cursor.getString(cursor.getColumnIndex(FeatureContract.Entry.DESCRIPTION))
            featureItem.imageHref =
                cursor.getString(cursor.getColumnIndex(FeatureContract.Entry.IMAGE_URL))
            featureList?.add(featureItem)
        }
        return featureList
    }

    @SuppressLint("Recycle")
    fun isTableEmpty(): Boolean {
        var empty = true
        val cursor: Cursor = database!!.rawQuery(
            "SELECT COUNT(*) FROM " + CityContract.Entry.CITY_TABLE,
            null
        )
        if (cursor.moveToFirst()) {
            empty = cursor.getInt(0) == 0
        }
        return empty
    }
}