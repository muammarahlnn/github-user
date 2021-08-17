package com.ardnn.githubuser

import android.content.Context
import android.content.res.TypedArray
import android.util.Log
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class UsersData(private val context: Context) {
    private val usersAva: TypedArray =
        context.resources.obtainTypedArray(R.array.users_ava)

    val userList: ArrayList<UserModel>
    get() {
        val list = arrayListOf<UserModel>()
        var inputStream: InputStream? = null
        var jsonString: String

        try {
            inputStream = context.assets.open("githubuser.json")
            jsonString = inputStream.bufferedReader().use {
                it.readText()
            }

            val jsonArr = JSONArray(jsonString)
            for (i in 0 until jsonArr.length()) {
                val data = jsonArr.getJSONObject(i)
                val user = UserModel(
                    username = data.getString("username"),
                    name = data.getString("name"),
                    company = data.getString("company"),
                    location = data.getString("location"),
                    repository = data.getInt("repository"),
                    follower = data.getInt("follower"),
                    following = data.getInt("following"),
                    avatar = usersAva.getResourceId(i, -1)
                )
                list.add(user)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }
        return list
    }

}