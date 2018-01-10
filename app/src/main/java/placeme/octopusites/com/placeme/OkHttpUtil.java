package placeme.octopusites.com.placeme;

/**
 * Created by admin on 1/10/2018.
 */
import javax.net.ssl.*;
import java.net.*;
import java.security.cert.CertificateException;
import okhttp3.OkHttpClient;

/*
okhttp version used 3.8.1
*/
public class OkHttpUtil {


    private static OkHttpClient client = null;
    private static boolean ignoreSslCertificate = false;

    public static OkHttpClient getClient() {
        return client;
    }

    public static void init(boolean ignoreCertificate) throws Exception {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (ignoreCertificate) {
            ignoreSslCertificate = true;
            builder = configureToIgnoreCertificate(builder);
        }

        //Other application specific configuration

        client = builder.build();
    }

    //Setting testMode configuration. If set as testMode, the connection will skip certification check
    private static OkHttpClient.Builder configureToIgnoreCertificate(OkHttpClient.Builder builder) {
        try {

            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
        }
        return builder;
    }

}