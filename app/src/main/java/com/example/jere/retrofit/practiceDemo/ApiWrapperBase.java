package com.example.jere.retrofit.practiceDemo;

import android.support.annotation.NonNull;
import android.util.Log;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;

/**
 * @author jere
 */
public abstract class ApiWrapperBase {

    public final static int VALUE_CONNECTION_TIMEOUT = 15;
    public final static int VALUE_WRITE_TIMEOUT = 15;
    public final static int VALUE_READ_TIMEOUT = 30;

    protected Retrofit mRetrofit;

    protected Object mService;

    protected abstract String getApiHost();

    protected ApiWrapperBase() {
        mRetrofit = createRetrofit(createClient());
    }

    @NonNull
    protected Retrofit createRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(getApiHost())
                .client(httpClient)
                .build();
    }

    public Object getService() {
        return mService;
    }

    @NonNull
    protected OkHttpClient createClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BASIC);
        return baseHttpClientBuilder()
                .hostnameVerifier((hostname, session) -> true)
                .addInterceptor(logging)
                .build();
    }


    protected OkHttpClient.Builder baseHttpClientBuilder() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(VALUE_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(VALUE_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(VALUE_READ_TIMEOUT, TimeUnit.SECONDS);

        // Create a trust manager that does not validate certificate chains
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                try {
                    chain[0].checkValidity();
                } catch (Exception e) {
                    throw new CertificateException("Certificate not valid or trusted");
                }
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            clientBuilder.sslSocketFactory(new CustomTLSSocketFactory(sslContext.getSocketFactory()), trustManager);

            ConnectionSpec cs11 = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_1)
                    .build();
            ConnectionSpec cs12 = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .build();

            List<ConnectionSpec> specs = new ArrayList<>();
            specs.add(cs11);
            specs.add(cs12);

            clientBuilder.connectionSpecs(specs);

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            Log.d("FairProductDiyApiHelper", e.getMessage());
        }
        return clientBuilder;
    }

}
