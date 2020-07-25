package com.example.githubproject.common.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


class AndroidUtil {
    companion object {
        fun hideSoftKeyboard(context: Context, view: View) {
            val imm =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}