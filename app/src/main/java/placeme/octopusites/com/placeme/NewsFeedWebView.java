package placeme.octopusites.com.placeme;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class NewsFeedWebView extends AppCompatActivity {

    WebView wb;
    Toolbar toolbar;
    ProgressBar webViewProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_web_view);

        webViewProgressBar = (ProgressBar) findViewById(R.id.webViewProgressBar);

        String headerURL = getIntent().getStringExtra("url");
        String headerTitle = getIntent().getStringExtra("title");

        ActionBar ab = getSupportActionBar();
        ab.setTitle(headerTitle);
        ab.setSubtitle(headerURL);
        ab.setDisplayHomeAsUpEnabled(true);

        wb = (WebView) findViewById(R.id.webView1);


        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.addJavascriptInterface(new WebAppInterface(NewsFeedWebView.this),"sunny");
//        wb.loadUrl("https://placeme.co.in/privacy-policy.jsp");
        wb.loadUrl(headerURL);
        wb.setWebViewClient(new MyWebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                progDailog.show();
                view.loadUrl(url);

                webViewProgressBar.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
//                progDailog.dismiss();
                webViewProgressBar.setVisibility(View.GONE);
            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }


}
