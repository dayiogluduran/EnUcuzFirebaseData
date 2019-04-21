package com.ddsoft.enucuzfirebasedata


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_update_price.*

class UpdatePriceFragment : Fragment() {

    val markets = arrayOf("Migros", "Bim", "CarrefourSa", "A101", "Şok")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_price, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, markets)
        spinner_updatePrice.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance(): UpdatePriceFragment = UpdatePriceFragment()
    }
}
