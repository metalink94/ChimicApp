package diplom.itis.chemistrydrawer.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import diplom.itis.chemistrydrawer.R;
import imangazaliev.scripto.Scripto;
import imangazaliev.scripto.ScriptoPrepareListener;

/**
 * Created by Денис on 29.04.2017.
 */

public class MarvinView extends WebView {


    private WebView mWebView;
    private ScriptoTest test;

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
        Scripto scripto = new Scripto.Builder(mWebView).build();
        test = scripto.create(ScriptoTest.class);
//        scripto.addInterface("Preferences", new WebAppInterface(getContext()));
        scripto.onPrepared(new ScriptoPrepareListener() {
            @Override
            public void onScriptoPrepared() {
                test.setTest().call();
                Log.d(getClass().getSimpleName(), "in Scripto");
            }
        });
        mWebView.setWebViewClient(new MyWebViewClient());
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        WebAppInterface webAppInterface = new WebAppInterface(getContext());
//        mWebView.addJavascriptInterface(webAppInterface, "android");
        // указываем страницу загрузки
        mWebView.loadUrl("file:///android_asset/editorws.html");
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

    public ScriptoTest getTest() {
        return test;
    }

    public MarvinView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init();
    }
}
