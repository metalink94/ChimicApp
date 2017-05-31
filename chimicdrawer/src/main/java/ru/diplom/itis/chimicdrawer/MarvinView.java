package ru.diplom.itis.chimicdrawer;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Денис on 29.04.2017.
 */

public class MarvinView extends WebView {


    private WebView mWebView;

    public MarvinView(Context context) {
        super(context);
        init();
    }

    public MarvinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarvinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MarvinView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.marvin_view, this);
        mWebView = (WebView) findViewById(R.id.marvinView);

        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromiumClient());
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        WebAppInterface webAppInterface = new WebAppInterface(getContext());
        mWebView.addJavascriptInterface(webAppInterface, "android");
        // указываем страницу загрузки
        mWebView.loadUrl("file:///android_asset/editorws.html");
    }

    private class MyWebChromiumClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(MarvinView.class.getSimpleName(),"Message = " + message);
            result.confirm();
            return true;

        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            Log.d(MarvinView.class.getSimpleName(),"Message = " + message);
            Log.d(MarvinView.class.getSimpleName(),"Message = " + defaultValue);
            return true;
        }
    }


    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }

    public MarvinView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init();
    }

    public void getData() {
        mWebView.loadUrl("javascript:alert(getMarvinStructure)");
        mWebView.loadUrl("javascript:prompt(getMarvinStructure)");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.evaluateJavascript("(function() { return getMarvinStructure();})();", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String aS) {
                    Log.d(this.getClass().getSimpleName(), aS);
                }
            });
        }
    }
}
