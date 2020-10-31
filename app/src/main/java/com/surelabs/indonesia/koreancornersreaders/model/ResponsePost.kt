package com.surelabs.e.jsoupbotapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponsePost(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: List<DataItemPost?>? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable

@Parcelize
data class DataItemPost(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("original_link")
    val originalLink: String? = null,

    @field:SerializedName("date_post")
    val datePost: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("slug_title")
    val slugTitle: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    @field:SerializedName("viewers")
    val viewers: String? = null,

    @field:SerializedName("shot_article")
    val shotArticle: String? = null
) : Parcelable
