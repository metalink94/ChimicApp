package diplom.itis.chemistrydrawer.activities;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.utils.BaseActivity;
import diplom.itis.chemistrydrawer.utils.WebAppInterface;

/**
 * Created by denis_000 on 06.12.2016.
 */
public class WebViewActivity extends BaseActivity{

    private WebView mWebView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setWebViewClient(new MyWebViewClient());
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
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

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
