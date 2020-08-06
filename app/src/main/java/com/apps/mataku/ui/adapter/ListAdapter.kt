package com.apps.mataku.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.apps.mataku.R
import com.apps.mataku.ui.list.butawarna.ListButaWarnaFregment
import com.apps.mataku.ui.list.katarak.ListKatarakFregment
import com.apps.mataku.ui.list.minus.ListMinusFregment
import com.apps.mataku.ui.list.pterigium.ListPterigiumFregment


class ListAdapter(fm: FragmentManager, private val context: Context?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> ListKatarakFregment.newInstance()
        1 -> ListMinusFregment.newInstance()
        2 -> ListButaWarnaFregment.newInstance()
        else ->  ListPterigiumFregment.newInstance()

    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> context!!.resources.getString(R.string.katarak)
        1 -> context!!.resources.getString(R.string.miopi)
        2 -> context!!.resources.getString(R.string.buta_warna)
        else -> context!!.resources.getString(R.string.pterigium)
    }

    override fun getCount(): Int = 4
}