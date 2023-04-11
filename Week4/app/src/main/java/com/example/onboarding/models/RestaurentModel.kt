package com.example.onboarding.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurentModel(
    val name: String?,
    val address: String?,
    val delivery_charge: String?,
    val image: String?,
    val hours: Hours?,
    var menus: List<Menus?>?
) : Parcelable

@Parcelize
data class Hours(
    val Sunday: String?,
    val Monday: String?,
    val Tuesday: String?,
    val Wednesday: String?,
    val Thursday: String?,
    val Friday: String?,
    val Saturday: String?
) : Parcelable

@Parcelize
data class Menus(
    val name: String?,
    val price: Float,
    val url: String?,
    var totalInCart: Int
) : Parcelable
