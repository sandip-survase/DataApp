package placeme.octopusites.com.placeme;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class NewsFeedWebView extends AppCompatActivity {

    WebView wb;
    Toolbar toolbar;
    ProgressBar webViewProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_web_view);

        webViewProgressBar= (ProgressBar) findViewById(R.id.webViewProgressBar);

        String headerURL=getIntent().getStringExtra("url");
        String headerTitle=getIntent().getStringExtra("header");

        ActionBar ab = getSupportActionBar();
        ab.setTitle(headerTitle);
        ab.setSubtitle(headerURL);
        ab.setDisplayHomeAsUpEnabled(true);

            wb=(WebView)findViewById(R.id.webView1);
        wb.setWebChromeClient(new WebChromeClient());
//            wb.getSettings().setJavaScriptEnabled(true);
//            wb.getSettings().setLoadWithOverviewMode(true);
//            wb.getSettings().setUseWideViewPort(true);
//            wb.getSettings().setBuiltInZoomControls(true);
//            wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//            wb.getSettings().setPluginState(WebSettings.PluginState.ON);
////            wb.getSettings().setPluginsEnabled(true);
//            wb.setWebViewClient(new HelloWebViewClient());
            wb.loadUrl(headerURL);

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

            webViewProgressBar.setVisibility(View.GONE);
            return false;
        }

    }
}
