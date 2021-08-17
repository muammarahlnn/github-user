package com.ardnn.githubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val username: String = "",
    val name: String = "",
    val company: String = "",
    val location: String = "",
    val repository: Int = 0,
    val follower: Int = 0,
    val following: Int = 0,
    val avatar: Int = 0
) : Parcelable
