package com.assignment.one.domain.repository

import android.content.Context
import com.assignment.one.domain.model.User
import com.assignment.one.local.loadData
import com.assignment.one.local.readFromAssets
import com.assignment.one.local.saveData

object LocalRepository {

    fun saveUserData(context: Context, userName: String, password: String) = saveData(context,userName,password)

    fun loadUserData(context: Context): User = loadData(context)

    fun loadJsonUserData(context: Context): String = readFromAssets(context)

}