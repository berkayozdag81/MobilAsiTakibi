package com.berkayozdag.mobilasitakibi.model

import java.io.Serializable


data class Vaccine(
    val id:String="",
    val name: String="",
    val dates: List<Dates>?= emptyList(),
):Serializable