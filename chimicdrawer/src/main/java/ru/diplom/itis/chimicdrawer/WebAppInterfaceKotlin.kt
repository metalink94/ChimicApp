package ru.diplom.itis.chimicdrawer

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface

/**
 * Created by Денис on 28.05.2017.
 */
class WebAppInterfaceKotlin {
    internal var mContext: Context

    constructor (ctx: Context): super(){
        this.mContext = ctx
    }


    @JavascriptInterface
    fun getMarvinStructure(data: Any) {
        //Get the string value to process
        Log.d("SendData", "send data from JS: " + data)
        //this.data = data
    }
}