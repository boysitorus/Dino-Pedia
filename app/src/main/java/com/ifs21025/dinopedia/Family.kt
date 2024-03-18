package com.ifs21025.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Family(
    var name: String,
    var pict: Int,
    var description: String,
    var periode: String,
    var characteristic: String,
    var habitat: String,
    var perilaku: String,
    var klasifikasi: String,
) : Parcelable