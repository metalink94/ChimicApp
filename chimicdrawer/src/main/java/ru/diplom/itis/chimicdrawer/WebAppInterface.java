package ru.diplom.itis.chimicdrawer;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by Денис on 22.04.2017.
 */

public class WebAppInterface {
    Context mContext;
    Object data;

    public WebAppInterface(Context ctx){
        this.mContext=ctx;
    }


    @JavascriptInterface
    public void getMarvinStructure(Object data) {
        //Get the string value to process
        Log.d("SendData", "send data from JS: " + data);
        this.data=data;
    }
}
