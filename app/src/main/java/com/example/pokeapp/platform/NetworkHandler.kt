package com.example.pokeapp.platform

import android.content.Context
import com.example.pokeapp.extension.networkInfo
import javax.inject.Inject

class NetworkHandler @Inject constructor(private val context: Context){
    val isConnected get() = context.networkInfo?.isConnected
}