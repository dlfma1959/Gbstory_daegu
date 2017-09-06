package com.daegu.gbstory_daegu;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    public WebView wv;
    private GoogleApiClient client;
    public String app_name = "gbstory_daegu";
    public String app_url = "http://gbstory.daegu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = (WebView) findViewById(R.id.wv);
        // 웹뷰에서 자바스크립트실행가능
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setUserAgentString(app_name);
        wv.loadUrl(app_url);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                String host = uri.getHost()==null?"":uri.getHost();
                String path = uri.getPath()==null?"":uri.getPath();
                String scheme = uri.getScheme()==null?"":uri.getScheme();

                if(scheme.equals("market") || host.equals("click.clickmon.co.kr")) {
                    return true;
                }else{
                    view.loadUrl(url);
                    return true;
                }
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (wv.canGoBack()) {
                wv.goBack();
            } else {
                finish();   //엑티비티종료
            }
        }
        return false;
    }
}
