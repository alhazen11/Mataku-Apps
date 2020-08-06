package com.apps.mataku.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Message {

    var userID: String? = null
    var username: String? = null
    var message: String? = null
    var timestamp: Long = 0
    var isNotification: Boolean = false

    constructor() {

    }

    constructor(userID: String, username: String, message: String, timestamp: Long, isNotification: Boolean) {
        this.userID = userID
        this.username = username
        this.message = message
        this.timestamp = timestamp
        this.isNotification = isNotification
    }

    override fun toString(): String {
        return "Message{" +
                "userID='" + userID + '\''.toString() +
                ", username='" + username + '\''.toString() +
                ", message='" + message + '\''.toString() +
                ", timestamp=" + timestamp +
                ", isNotification=" + isNotification +
                '}'.toString()
    }

    //might want to improve this
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true
        val otherMessage = other as Message?
        return if (this.message!! + this.username!! == otherMessage!!.message!! + otherMessage.username!!) {
            true
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return username!!.hashCode() * message!!.hashCode()
    }
}
