package com.ddsoft.enucuzfirebasedata

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_add_product.*

class AddProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnReadBarcode_addProduct.setOnClickListener {
            val scanner = IntentIntegrator(activity)
            scanner.setBeepEnabled(false)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
            scanner.initiateScan()
        }

        btnSaveProduct_addProduct.setOnClickListener {
            if (!txtProductBarcode_addProduct.text.isEmpty() && !txtProductName_addProduct.text.isEmpty()) {

                registerProducttoDatabase()

            } else {
                Toast.makeText(activity, "Lütfen boş alan bırakmayınız!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): AddProductFragment = AddProductFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    txtProductBarcode_addProduct.setText(result.contents)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun registerProducttoDatabase() {

        val rootRef = FirebaseDatabase.getInstance().reference
        rootRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild("/products/${txtProductBarcode_addProduct.text}")) {
                    Toast.makeText(activity, "Ürün daha önce kayıt edilmiştir.", Toast.LENGTH_SHORT).show()
                    txtProductName_addProduct.setText("")
                    txtProductBarcode_addProduct.setText("")
                } else {
                    val ref =
                        FirebaseDatabase.getInstance().getReference("/products/${txtProductBarcode_addProduct.text}")
                    val refMar = FirebaseDatabase.getInstance()
                        .getReference("/products/${txtProductBarcode_addProduct.text}/markets")
                    val product =
                        Product(txtProductBarcode_addProduct.text.toString(), txtProductName_addProduct.text.toString())
                    val markets = Markets(0.0, 0.0, 0.0, 0.0, 0.0)
                    ref.setValue(product)
                        .addOnSuccessListener {
                            Toast.makeText(activity, "Ürün başarıyla kayıt edildi.", Toast.LENGTH_SHORT).show()
                            txtProductName_addProduct.setText("")
                            txtProductBarcode_addProduct.setText("")
                        }
                    refMar.setValue(markets)
                        .addOnSuccessListener {
                            Toast.makeText(activity, "Ürün başarıyla kayıt edildi.", Toast.LENGTH_SHORT).show()
                            txtProductName_addProduct.setText("")
                            txtProductBarcode_addProduct.setText("")
                        }
                }
            }
        })

    }

}
