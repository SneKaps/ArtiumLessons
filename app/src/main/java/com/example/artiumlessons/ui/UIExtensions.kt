package com.example.artiumlessons.ui

import android.content.Context
import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.widget.Toast

fun Window.setupRootViewInsetsAndStatusBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
        this.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
            view.setBackgroundColor(color)
            view.setPadding(0, statusBarInsets.top, 0, 0)
            insets
        }
    } else {
        this.decorView.setPadding(0, 16, 0, 0)
        this.statusBarColor = color
    }
}

fun Context.showToast(
    toastContent: String,
    toastLength: Int = Toast.LENGTH_LONG
) {
    Toast.makeText(
        this,
        toastContent,
        toastLength
    ).show()
}