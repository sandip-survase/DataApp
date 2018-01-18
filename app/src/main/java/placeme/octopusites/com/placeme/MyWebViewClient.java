package placeme.octopusites.com.placeme;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by admin on 1/17/2018.
 */
public class MyWebViewClient extends WebViewClient {
    private String TAG = "CustomWebViewClientSunny";

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().equals("https://placeme.co.in/privacy-policy.jsp")) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
        }
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        return true;
    }

    private void startActivity(Intent intent) {
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        String message = "SSL Certificate error.";
        switch (error.getPrimaryError()) {
            case SslError.SSL_UNTRUSTED:
                message = "The certificate authority is not trusted.";
                Log.d(TAG, "onReceivedSslError: "+message);
                break;
            case SslError.SSL_EXPIRED:
                message = "The certificate has expired.";
                Log.d(TAG, "onReceivedSslError: "+message);

                break;
            case SslError.SSL_IDMISMATCH:
                message = "The certificate Hostname mismatch.";
                Log.d(TAG, "onReceivedSslError: "+message);

                break;
            case SslError.SSL_NOTYETVALID:
                message = "The certificate is not yet valid.";
                Log.d(TAG, "onReceivedSslError: "+message);

                break;
        }
        handler.proceed(); // Ignore SSL certificate errors

    }

    @Override
    public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
        super.onSafeBrowsingHit(view, request, threatType, callback);
    }
}