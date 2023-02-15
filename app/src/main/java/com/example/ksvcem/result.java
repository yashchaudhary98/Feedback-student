package com.example.ksvcem;

import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class result extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        getSupportActionBar().hide();

        webView = findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://erp.aktu.ac.in/webpages/oneview/oneview.aspx");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){

            webView.goBack();

        }else{

            super.onBackPressed();

        }

    }
}