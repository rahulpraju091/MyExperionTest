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

class FeatureDAO(private var context: Context) : DbDao(context) {

    fun insertCityDetailsToDB(response: CityModel): Long {
        var insert: Long = 0
        val values = ContentValues()
        values.put(CityContract.Entry.TITLE, response.title)
        insert = database!!.insert(CityContract.Entry.CITY_TABLE, null, values)
        return insert
    }

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

    fun truncateAllTables() {
        val sqlCity = "DELETE FROM " + CityContract.Entry.CITY_TABLE
        val sqlFeature = "DELETE FROM " + FeatureContract.Entry.FEATURE_TABLE
        database!!.execSQL(sqlCity)
        database!!.execSQL(sqlFeature)
    }

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
}