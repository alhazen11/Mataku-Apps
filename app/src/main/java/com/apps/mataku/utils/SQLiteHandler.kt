package com.apps.mataku.utils


/**
 * Created by zahid on 9/2/2017.
 */

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

import java.util.HashMap


class SQLiteHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /**
     * Getting user data from database
     */
    // Move to first row
    // return user
    val userDetails: HashMap<String, String>
        get() {
            val user = HashMap<String, String>()
            val selectQuery = "SELECT  * FROM $TABLE_USER"

            val db = this.readableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            cursor.moveToFirst()
            if (cursor.count > 0) {
                user["uid"] = cursor.getString(1)
                user["name"] = cursor.getString(2)
                user["email"] = cursor.getString(3)
                user["foto"] = cursor.getString(4)
                user["dokter"] = cursor.getString(5)
                user["key"] = cursor.getString(6)
            }
            cursor.close()
            db.close()
            Log.d(TAG, "Fetching user from Sqlite: $user")

            return user
        }

    fun updateUser(id: String,name: String, email: String, foto: String, level: Int, key: String):Boolean {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_NAME, name) // Name
        values.put(KEY_EMAIL, email) // Email
        values.put(KEY_FOTO, foto) // Email
        values.put(KEY_LEVEL, level) // Created At
        values.put(KEY_KEY, key) // Created At

        db.update(TABLE_USER, values, "uid = ?", arrayOf(id))
        return true
    }


    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_LOGIN_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UID + " INTEGER," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_FOTO + " TEXT,"
                + KEY_LEVEL + " INTEGER,"
                + KEY_KEY + " TEXT"
                + ")")
        db.execSQL(CREATE_LOGIN_TABLE)

        Log.d(TAG, "Database tables created")
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")

        // Create tables again
        onCreate(db)
    }

    /**
     * Storing user details in database
     */
    fun addUser(id: Int,name: String, email: String, foto: String, level: Int, key: String) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_UID, id) // Name
        values.put(KEY_NAME, name) // Name
        values.put(KEY_EMAIL, email) // Email
        values.put(KEY_FOTO, foto) // Email
        values.put(KEY_LEVEL, level) // Created At
        values.put(KEY_KEY, key) // Created At

        // Inserting Row
        val id = db.insert(TABLE_USER, null, values)
        db.close() // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: $id")
    }

    /**
     * Re crate database Delete all tables and create them again
     */
    fun deleteUsers() {
        val db = this.writableDatabase
        // Delete All Rows
        db.delete(TABLE_USER, null, null)
        db.close()

        Log.d(TAG, "Deleted all user info from sqlite")
    }

    companion object {

        private val TAG = SQLiteHandler::class.java.simpleName

        // All Static variables
        // Database Version
        private val DATABASE_VERSION = 3

        // Database Name
        private val DATABASE_NAME = "smarthima"

        // Login table name
        private val TABLE_USER = "user"

        // Login Table Columns names
        private val KEY_ID = "id"
        private val KEY_UID = "uid"
        private val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
        private val KEY_FOTO = "foto"
        private val KEY_LEVEL = "dokter"
        private val KEY_KEY = "key"

    }

}