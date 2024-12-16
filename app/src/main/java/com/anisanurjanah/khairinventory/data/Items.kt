package com.anisanurjanah.khairinventory.data

import com.google.firebase.database.IgnoreExtraProperties
import java.util.UUID

@IgnoreExtraProperties
data class Items(
    val id: String = UUID.randomUUID().toString(),
    val name: String? = null,
    val category: String? = null,
    val quantity: Int? = null,
    val location: String? = null,
    val status: String? = null,
    val timestamp: Long = System.currentTimeMillis()
) {

}
