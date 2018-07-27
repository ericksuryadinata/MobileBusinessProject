package com.proesd.erick.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.webview1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new MyBrowser());
        webview.loadUrl("https://latihansbmptn.com");
    }

    private class MyBrowser extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url){
            webview.loadUrl(url);
            return true;
        }
    }
}
