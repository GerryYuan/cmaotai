package com.cmaotai.service.httpproxy;

import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class ProxyIp {

    public static String authHeader() {
        String orderno = "ZF2018653868BkaoGn";
        String secret = "3eccb1c266c4407abdd8a2e17d02f5cd";
        long timestamp = new Date().getTime() / 1000;
        //拼装签名字符串
        String planText = String.format("orderno=%s,secret=%s,timestamp=%d", orderno, secret, timestamp);
        //计算签名
        String sign = Hashing.md5().newHasher().putString(planText, Charset.defaultCharset()).hash().toString()
            .toUpperCase();
        //拼装请求头Proxy-Authorization的值
        String authHeader = String.format("sign=%s&orderno=%s&timestamp=%d", sign, orderno, timestamp);
        return authHeader;
    }

//    public static HttpComponentsClientHttpRequestFactory getHttpRequestFactory(HttpHeaders httpHeaders) {
//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//        try {
//            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//                .loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
//            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//            CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(csf).setProxy(new HttpHost("forward.xdaili.cn", 80, "http"))
//                .build();
//            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//            requestFactory.setReadTimeout(10000);
//            requestFactory.setConnectTimeout(10000);
//            requestFactory.setHttpClient(httpClient);
//            httpHeaders.add("Proxy-Authorization", authHeader());
//            return requestFactory;
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }


    public static SimpleClientHttpRequestFactory getHttpRequestFactory(HttpHeaders httpHeaders) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(10000);
        requestFactory.setConnectTimeout(10000);
        return requestFactory;
    }

}
