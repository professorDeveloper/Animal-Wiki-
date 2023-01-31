package com.azamovhudstc.animalwiki.data.local.sharedpref


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.azamovhudstc.animalwiki.utils.enums.ScreenEnum
import com.azamovhudstc.animalwiki.utils.extensions.screenEnum
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppReference @Inject  constructor(
    @ApplicationContext
    context: Context,
) {
    private var sharedPref: SharedPreferences = context.getSharedPreferences("auth", MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPref.edit()


    fun setToken(token: String) {
        editor.putString("TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString("TOKEN", "")
    }

    var currentScreen: ScreenEnum
        get() = sharedPref.getString("CURRENT_SCREEN", ScreenEnum.LOGIN._name)!!.screenEnum()
        set(value) {
            sharedPref.edit().putString("CURRENT_SCREEN", value._name).apply()
        }
    var userName: String
        set(value) = sharedPref.edit().putString("USERNAME", value).apply()
        get() = sharedPref.getString("USERNAME", "")!!

    var verifyToken: String
        set(value) = sharedPref.edit().putString("VERIFY_TOKEN", value).apply()
        get() = sharedPref.getString("VERIFY_TOKEN", "")!!

    fun clear() {
        editor.clear()
    }
}