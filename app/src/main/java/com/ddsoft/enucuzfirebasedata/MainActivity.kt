package com.ddsoft.enucuzfirebasedata

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.BaseOnTabSelectedListener<TabLayout.Tab>,
    ViewPager.OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        tabLayout.setupWithViewPager(viewPager)

        viewPager.currentItem = 0

        tabLayout.addOnTabSelectedListener(this)
        viewPager.addOnPageChangeListener(this)
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
        Log.v("TAG", "onTabReselected")
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
        Log.v("TAG", "onTabUnselected")
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        Log.v("TAG", "onTabSelected")
    }

    override fun onPageScrollStateChanged(state: Int) {
        Log.v("TAG", "onPageScrollStateChanged")
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.v("TAG", "onPageScrolled")
    }

    override fun onPageSelected(position: Int) {
        Log.v("TAG", "onPageSelected")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }

    }
}

class Product(val barcode: String, val name: String)
class Markets(val migros: Double, val bim: Double, val carrefour: Double, val a101: Double, val sok: Double)
