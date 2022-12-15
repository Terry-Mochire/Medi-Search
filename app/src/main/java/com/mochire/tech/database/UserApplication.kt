package com.mochire.tech.database

import android.app.Application

class UserApplication: Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
}