package com.example.lab2_part3;

import android.content.Intent; // <-- IMPORTANTE
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenWebsite = findViewById(R.id.OpenWebsite);
        Button btnOpenMap     = findViewById(R.id.OpenMap);   // <-- AÑADIR

        myWebView = findViewById(R.id.webview);

        WebView.setWebContentsDebuggingEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        myWebView.clearCache(true);
        myWebView.clearHistory();

        // Botón Web
        btnOpenWebsite.setOnClickListener(v -> {
            myWebView.loadUrl("https://ok.ubc.ca/");
        });

        // Botón Map
        btnOpenMap.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(i);
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (myWebView.canGoBack()) {
                    myWebView.goBack();
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }
}


