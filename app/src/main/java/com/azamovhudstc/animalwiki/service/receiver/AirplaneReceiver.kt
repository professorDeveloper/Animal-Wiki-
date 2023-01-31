package com.azamovhudstc.animalwiki.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class AirplaneReceiver :BroadcastReceiver() {
    private  var listener :((Boolean)->Unit) ={}
    fun setListener(f : (Boolean)->Unit){
        listener = f
    }
    override fun onReceive(p0: Context?, intent: Intent?) {

        listener.invoke(isOnline(p0!!))
    }
    companion object{
        lateinit var networkReceiver: AirplaneReceiver
        fun getInstance(): AirplaneReceiver {
            if(!::networkReceiver.isInitialized){
                networkReceiver=AirplaneReceiver()
            }

            return  AirplaneReceiver()
        }

    }
    private fun isOnline(context: Context):Boolean{
        return try {
            val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            (networkInfo!=null && networkInfo.isConnected)
        }catch (e:NullPointerException){
            println(e.printStackTrace())
            false
        }

    }

}