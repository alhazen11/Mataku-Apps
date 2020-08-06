package com.apps.mataku.ui.list

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.apps.mataku.ui.adapter.ListAdapter
import com.apps.mataku.R
import com.apps.mataku.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val adapter = ListAdapter(supportFragmentManager,applicationContext)
        ui_frame.adapter = adapter
        ui_tabs.setupWithViewPager(ui_frame)
        ui_tabs.tabMode = TabLayout.MODE_FIXED
        ui_back.setOnClickListener { startActivity(Intent(this@ListActivity, DashboardActivity::class.java)) }
    }
}
