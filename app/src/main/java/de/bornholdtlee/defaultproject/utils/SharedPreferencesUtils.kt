package de.bornholdtlee.defaultproject.utils

import android.content.Context
import android.content.SharedPreferences

import java.util.HashSet

import de.bornholdtlee.defaultproject.R

class SharedPreferencesUtils(context: Context) {

    private val sharedPreferencesReader: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    private val sharedPreferencesWriter: SharedPreferences.Editor

    init {
        sharedPreferencesWriter = sharedPreferencesReader.edit()
    }

    fun putString(key: String, value: String) {
        sharedPreferencesWriter.putString(key, value)
        sharedPreferencesWriter.apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferencesWriter.putBoolean(key, value)
        sharedPreferencesWriter.apply()
    }

    fun putInt(key: String, value: Int) {
        sharedPreferencesWriter.putInt(key, value)
        sharedPreferencesWriter.apply()
    }

    fun putLong(key: String, value: Long?) {
        sharedPreferencesWriter.putLong(key, value!!)
        sharedPreferencesWriter.apply()
    }

    fun remove(key: String) {
        sharedPreferencesWriter.remove(key)
        sharedPreferencesWriter.apply()
    }

    fun removeAll() {
        sharedPreferencesWriter.clear().apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPreferencesReader.getString(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferencesReader.getBoolean(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferencesReader.getLong(key, defaultValue)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferencesReader.getInt(key, defaultValue)
    }

    fun putFloat(key: String, value: Float) {
        sharedPreferencesWriter.putFloat(key, value)
        sharedPreferencesWriter.apply()
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferencesReader.getFloat(key, defaultValue)
    }

    fun addToKeySet(key: String, setEntry: String) {
        val set = getKeySet(key)
        set.add(setEntry)
        sharedPreferencesWriter.putStringSet(key, set)
        sharedPreferencesWriter.apply()
    }

    fun getKeySet(key: String): HashSet<String> {
        return HashSet(sharedPreferencesReader.getStringSet(key, HashSet())!!)
    }
}