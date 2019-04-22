package com.ddsoft.enucuzfirebasedata


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
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

        btnReadBarcode_updatePrice.setOnClickListener {
            val scanner = IntentIntegrator(activity)
            scanner.setBeepEnabled(false)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
            scanner.initiateScan()
        }

        btnSaveProduct_updatePrice.setOnClickListener {


            if (!txtProductBarcode_updatePrice.text.isEmpty() && !txtProductPrice_updatePrice.text.isEmpty()) {
                val market = spinner_updatePrice.selectedItem.toString()
                registerProducttoDatabase(market)

            } else {
                Toast.makeText(activity, "Lütfen boş alan bırakmayınız!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): UpdatePriceFragment = UpdatePriceFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    txtProductBarcode_updatePrice.setText(result.contents)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun registerProducttoDatabase(market: String) {

        val rootRef = FirebaseDatabase.getInstance().reference
        rootRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild("/products/${txtProductBarcode_updatePrice.text}")) {

                    when (market) {
                        "Migros" -> {
                            val ref =
                                FirebaseDatabase.getInstance()
                                    .getReference("/products/${txtProductBarcode_updatePrice.text}/markets/migros")
                            ref.setValue(txtProductPrice_updatePrice.text.toString().toDouble())
                                .addOnSuccessListener {
                                    Toast.makeText(activity, "Başarılı", Toast.LENGTH_SHORT).show()
                                }
                        }
                        "Şok" -> {
                            val ref =
                                FirebaseDatabase.getInstance()
                                    .getReference("/products/${txtProductBarcode_updatePrice.text}/markets/sok")
                            ref.setValue(txtProductPrice_updatePrice.text.toString().toDouble())
                                .addOnSuccessListener {
                                    Toast.makeText(activity, "Başarılı", Toast.LENGTH_SHORT).show()
                                }
                        }
                        "A101" -> {
                            val ref =
                                FirebaseDatabase.getInstance()
                                    .getReference("/products/${txtProductBarcode_updatePrice.text}/markets/a101")
                            ref.setValue(txtProductPrice_updatePrice.text.toString().toDouble())
                                .addOnSuccessListener {
                                    Toast.makeText(activity, "Başarılı", Toast.LENGTH_SHORT).show()
                                }
                        }
                        "Bim" -> {
                            val ref =
                                FirebaseDatabase.getInstance()
                                    .getReference("/products/${txtProductBarcode_updatePrice.text}/markets/bim")
                            ref.setValue(txtProductPrice_updatePrice.text.toString().toDouble())
                                .addOnSuccessListener {
                                    Toast.makeText(activity, "Başarılı", Toast.LENGTH_SHORT).show()
                                }
                        }
                        "CarrefourSa" -> {
                            val ref =
                                FirebaseDatabase.getInstance()
                                    .getReference("/products/${txtProductBarcode_updatePrice.text}/markets/carrefour")
                            ref.setValue(txtProductPrice_updatePrice.text.toString().toDouble())
                                .addOnSuccessListener {
                                    Toast.makeText(activity, "Başarılı", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                } else {
                    Toast.makeText(activity, "Ürün bulunamadı.", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    inline fun String.toDouble(): Double = java.lang.Double.parseDouble(this)
}

