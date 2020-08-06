package com.apps.mataku.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.util.Log

class SessionManager(private val _context: Context) {

    // Shared Preferences
    private val pref: SharedPreferences

    private val editor: Editor

    // Shared pref mode
    private val PRIVATE_MODE = 0

    val isLoggedIn: Boolean
        get() = pref.getBoolean(KEY_IS_LOGGEDIN, false)

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun setLogin(isLoggedIn: Boolean) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn)

        // commit changes
        editor.commit()

        Log.d(TAG, "User login session modified!")
    }

    companion object {
        // LogCat tag
        private val TAG = SessionManager::class.java.simpleName

        // Shared preferences file name
        private val PREF_NAME = "smarthima_login"

        private val KEY_IS_LOGGEDIN = "isLoggedIn"
    }
}