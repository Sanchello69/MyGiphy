package com.vas.mygiphy.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class GifsModelData(
    @SerializedName("data")
    val data: List<GifData>,
)

@Parcelize
data class GifData(
    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("rating")
    val rating: String,

    @SerializedName("import_datetime")
    val datetime: String,

    @SerializedName("images")
    val images: Images,
): Parcelable

@Parcelize
data class Images(
    @SerializedName("fixed_height")
    val gifUrl: GifUrl,
): Parcelable

@Parcelize
data class GifUrl(
    @SerializedName("url")
    val url: String
): Parcelable


