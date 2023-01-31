package com.azamovhudstc.animalwiki.utils.extensions

import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.azamovhudstc.animalwiki.utils.enums.ScreenEnum
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnack(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Snackbar.make(requireView(), message, duration).show()
}


fun String.screenEnum(): ScreenEnum {
    return when (this) {
        "LOGIN" -> ScreenEnum.LOGIN
        else -> ScreenEnum.HOME
    }
}