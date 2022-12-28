package com.berkayozdag.mobilasitakibi.model

import java.util.*

data class Vaccine(
    val name: String,
    //val vaccineDate: String,
    //val hasTaken: Boolean,
    val dates: List<Dates>? = null
): java.io.Serializable
