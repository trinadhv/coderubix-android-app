package org.coderubix.forum.coderubix;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    private WebView codeRubixWebView;
    private SwipeRefreshLayout pullRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codeRubixWebView = (WebView) findViewById(R.id.webView);

        codeRubixWebView.loadUrl("http://forum.coderubix.org");
        codeRubixWebView.setWebViewClient(new WebViewClient());

        pullRefresh=(SwipeRefreshLayout)this.findViewById(R.id.swipeRefreshContainer);

        WebSettings webSettings = codeRubixWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        pullRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh(){
                refreshContent();

            }


        });

    }
    public void refreshContent(){

        codeRubixWebView.reload();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              pullRefresh.setRefreshing(false);
            }
        },1000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && codeRubixWebView.canGoBack()) {
            codeRubixWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


    //TODO
    /*

    Change the color of the app header to match with the color of the selected discussion tag
    Listen for the URL change in the webview and check for the name of tag.
     */


}


