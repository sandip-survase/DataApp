package placeme.octopusites.com.placeme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsFeedWebView extends AppCompatActivity {

    WebView wb;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_web_view);

        String headerURL=getIntent().getStringExtra("url");

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Title");
        ab.setSubtitle(headerURL);
        ab.setDisplayHomeAsUpEnabled(true);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("My Profile");


            wb=(WebView)findViewById(R.id.webView1);
            wb.getSettings().setJavaScriptEnabled(true);
//            wb.getSettings().setLoadWithOverviewMode(true);
            wb.getSettings().setUseWideViewPort(true);
            wb.getSettings().setBuiltInZoomControls(true);
            wb.getSettings().setPluginState(WebSettings.PluginState.ON);
//            wb.getSettings().setPluginsEnabled(true);
            wb.setWebViewClient(new HelloWebViewClient());
            wb.loadUrl(headerURL);
//            wb.loadUrl("https://en.wikipedia.org/wiki/Ford_Mustang");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case android.R.id.home:
                onBackPressed();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
