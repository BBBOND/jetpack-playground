/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bbbond.playground.windowpreferences

import android.R
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.core.graphics.ColorUtils
import com.google.android.material.color.MaterialColors

/**
 * Helper that saves the current window preferences for the Catalog.
 */
class WindowPreferencesManager(private val context: Context) {

    fun applyEdgeToEdgePreference(window: Window, edgeToEdgeEnabled: Boolean = true) {
        if (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
            return
        }
        val statusBarColor = getStatusBarColor(true)
        val navbarColor = getNavBarColor(true)
        val lightBackground = MaterialColors.isColorLight(
            MaterialColors.getColor(
                context, R.attr.colorBackground, Color.BLACK
            )
        )
        val lightStatusBar = MaterialColors.isColorLight(statusBarColor)
        val showDarkStatusBarIcons =
            lightStatusBar || statusBarColor == Color.TRANSPARENT && lightBackground
        val lightNavbar = MaterialColors.isColorLight(navbarColor)
        val showDarkNavbarIcons = lightNavbar || navbarColor == Color.TRANSPARENT && lightBackground
        val decorView = window.decorView
        val currentStatusBar =
            if (showDarkStatusBarIcons && VERSION.SDK_INT >= VERSION_CODES.M) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
        val currentNavBar =
            if (showDarkNavbarIcons && VERSION.SDK_INT >= VERSION_CODES.O) View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR else 0
        window.navigationBarColor = navbarColor
        window.statusBarColor = statusBarColor
        val systemUiVisibility =
            ((if (edgeToEdgeEnabled) EDGE_TO_EDGE_FLAGS else View.SYSTEM_UI_FLAG_VISIBLE)
                    or currentStatusBar
                    or currentNavBar)
        decorView.systemUiVisibility = systemUiVisibility
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    private fun getStatusBarColor(isEdgeToEdgeEnabled: Boolean): Int {
        if (isEdgeToEdgeEnabled && VERSION.SDK_INT < VERSION_CODES.M) {
            val opaqueStatusBarColor =
                MaterialColors.getColor(context, R.attr.statusBarColor, Color.BLACK)
            return ColorUtils.setAlphaComponent(opaqueStatusBarColor, EDGE_TO_EDGE_BAR_ALPHA)
        }
        return if (isEdgeToEdgeEnabled) {
            Color.TRANSPARENT
        } else MaterialColors.getColor(
            context,
            R.attr.statusBarColor,
            Color.BLACK
        )
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    private fun getNavBarColor(isEdgeToEdgeEnabled: Boolean): Int {
        if (isEdgeToEdgeEnabled && VERSION.SDK_INT < VERSION_CODES.O_MR1) {
            val opaqueNavBarColor =
                MaterialColors.getColor(context, R.attr.navigationBarColor, Color.BLACK)
            return ColorUtils.setAlphaComponent(opaqueNavBarColor, EDGE_TO_EDGE_BAR_ALPHA)
        }
        return if (isEdgeToEdgeEnabled) {
            Color.TRANSPARENT
        } else MaterialColors.getColor(
            context,
            R.attr.navigationBarColor,
            Color.BLACK
        )
    }

    companion object {
        private const val EDGE_TO_EDGE_BAR_ALPHA = 128

        @RequiresApi(VERSION_CODES.LOLLIPOP)
        private val EDGE_TO_EDGE_FLAGS = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}