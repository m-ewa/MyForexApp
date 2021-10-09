package com.ewam.myforexapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    var success: Boolean,
    var timestamp: Long?,
    var base: String?,
    var date: String?,
    var rates: Map<String, Double>
) : Parcelable