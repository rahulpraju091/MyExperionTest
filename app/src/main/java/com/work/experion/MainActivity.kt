package com.work.experion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = ""
        setSupportActionBar(findViewById(R.id.toolbar))
        val ft =
            supportFragmentManager.beginTransaction()
        val fragment: Fragment = FeatureListFragment.newInstance()
        ft.replace(R.id.content_home, fragment)
        ft.commit()
    }
}