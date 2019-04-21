package com.ddsoft.enucuzfirebasedata

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    private val addNewProduct by lazy { AddProductFragment.newInstance() }
    private val updatePrice by lazy { UpdatePriceFragment.newInstance() }

    val fragmentList = ArrayList<Fragment>().apply {
        add(addNewProduct)
        add(updatePrice)
    }

    val fragmentTitleList = ArrayList<String>().apply {
        add("Yeni Ürün")
        add("Fiyat Güncelle")
    }

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = fragmentTitleList[position]
}