package com.androidians.getlocationupdates.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.app.ActivityCompat


fun Activity.hasPermission(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun Activity.findAndSetText(view: TextView, text: String) {
    view.text = text
}

fun Activity.showLocation(view: TextView, location: Location) {
    findAndSetText(view, location.asString(Location.FORMAT_MINUTES))
}
