package com.example.pokeapp.domain.preferences

import android.content.Context
import java.util.*

class SharedPreferences(context: Context) {

    companion object {
        const val LOGGED_IN = "IS_LOGGED_IN"
    }

    private val preferences = context.getSharedPreferences("POKEAPP_PREFERENCES", Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun <T: Any> getData(clazz: T, key: String): Any? {
        return when (clazz){
            is Boolean.Companion -> {
                preferences.getBoolean(key, false)
            }
            is Int.Companion -> {
                preferences.getInt(key, 0)
            }
            is String.Companion -> {
                preferences.getString(key, null)
            }
            is Long.Companion -> {
                preferences.getLong(key, 0)
            }
            is Date -> {
                Date(preferences.getLong(key, 0))
            }
            else -> null
        }
    }

    fun <T: Any> setData(value: T, key: String) {
        when (value){
            is Boolean -> {
                editor.putBoolean(key,value)
            }
            is Int -> {
                editor.putInt(key,value)
            }
            is String -> {
                editor.putString(key,value)
            }
            is Long -> {
                editor.putLong(key,value)
            }
        }
        editor.commit()
    }




}