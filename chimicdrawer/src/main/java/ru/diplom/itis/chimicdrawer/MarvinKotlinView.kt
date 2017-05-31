package ru.diplom.itis.chimicdrawer

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.*

/**
 * Created by Денис on 28.05.2017.
 */
public class MarvinKotlinView : WebView {

    private var mWebView: WebView? = null

    constructor (context: Context): super(context) {
        init()
    }
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        View.inflate(context, R.layout.marvin_view, this)
        mWebView = findViewById(R.id.marvinView) as WebView
        (mWebView as WebView).setWebViewClient(MyWebViewClient())
        (mWebView as WebView).setWebChromeClient(MyWebChromiumClient())
        // включаем поддержку JavaScript
        (mWebView as WebView).getSettings().javaScriptEnabled = true
        val webAppInterface = WebAppInterfaceKotlin(context)
        (mWebView as WebView).addJavascriptInterface(webAppInterface, "android")
        // указываем страницу загрузки
        (mWebView as WebView).loadUrl("file:///android_asset/editorws.html")
    }

    private inner class MyWebChromiumClient : WebChromeClient() {
        override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
            Log.d(this::class.java.simpleName, "onJsAlert Message = " + message)
            result.confirm()
            return true

        }

        override fun onJsPrompt(view: WebView, url: String, message: String, defaultValue: String, result: JsPromptResult): Boolean {
            Log.d(this::class.java.simpleName, "onJsPrompt Message = " + message)
            Log.d(this::class.java.simpleName, "onJsPrompt Message = " + defaultValue)
            return true
        }
    }


    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    fun getData() {
        (mWebView as WebView).loadUrl("javascript:alert(marvin.sketcherInstance.exportStructure(\"mrv\"))")
        (mWebView as WebView).loadUrl("javascript:prompt(marvin.sketcherInstance.exportStructure(\"mrv\"))")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            (mWebView as WebView).evaluateJavascript("(function() { getMarvinStructure();})();"
                    , object : ValueCallback<String> {
                override fun onReceiveValue(aS: String) {
                    Log.d(this.javaClass.simpleName,"onReceiveValue " + aS)
                }
            })
        }
    }

}