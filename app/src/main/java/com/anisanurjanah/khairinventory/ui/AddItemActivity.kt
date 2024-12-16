package com.anisanurjanah.khairinventory.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anisanurjanah.khairinventory.data.Items
import com.anisanurjanah.khairinventory.databinding.ActivityAddItemBinding
import com.anisanurjanah.khairinventory.utils.INVENTORY
import com.anisanurjanah.khairinventory.utils.ITEMS
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import java.util.UUID

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding
    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.database
        val inventoryRef = db.reference.child(INVENTORY).child(ITEMS)

        binding.btnSave.setOnClickListener {
            val name = binding.edName.text.toString()
            val category = binding.edCategory.text.toString()
            val quantity = binding.edQty.text.toString().toIntOrNull() ?: 0
            val location = binding.edLocation.text.toString()
            val status = binding.edStatus.text.toString()

            if (name.isBlank() || category.isBlank() || location.isBlank() || status.isBlank()) {
                Toast.makeText(this, "Semua kolom wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val itemId = inventoryRef.push().key ?: UUID.randomUUID().toString()
            val item = Items(
                id = itemId,
                name = name,
                category = category,
                quantity = quantity,
                location = location,
                status = status,
                timestamp = System.currentTimeMillis()
            )

            inventoryRef.child(itemId).setValue(item)
                .addOnSuccessListener {
                    Toast.makeText(this, "Barang berhasil disimpan", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Gagal menyimpan barang: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }
}