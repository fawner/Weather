package com.fast.weather;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by 亲爱的~ on 2016/11/4.
 */
public class QueryGET {
    //url参数地址定义
    private String urlRoot = "https://api.thinkpage.cn/v3";
    //url访问文件定义
    private String urlFileWeatherNow = "/weather/now.json";
    private String urlFileWeatherDaily = "/weather/daily.json";
    private String urlFileLifeSuggestion = "/life/suggestion.json";
    private String urlFileLocationSearch = "/location/search.json";
    //urlAPI密钥定义
    private String urlkey = "?key=ihgy05vjoxn9kqig";
    //url查询城市定义
    private String urlLocation = "&location=";
    //url语言定义
    private String urlLanguage = "&language=zh-Hans";
    //url返回语言定义
    private String urlUnit = "&unit=c";
    //url预报起始时间定义（可选）
    private String urlStart = "&start=0";
    //url预报天数定义（可选）
    private String urlDays = "&days=5";
    //url城市搜索q定义
    private String urlQ = "&q=";

    private String httpsUrl = "";

    public QueryGET(){

    }

    public JSONObject getWeatherNow(String location){
        httpsUrl = urlRoot + urlFileWeatherNow + urlkey + urlLocation + location + urlLanguage + urlUnit;
        return GetHttps(httpsUrl);
    }
    public JSONObject getWeatherDaily(String location){
        httpsUrl = urlRoot + urlFileWeatherDaily + urlkey + urlLocation + location + urlLanguage + urlUnit + urlStart + urlDays;
        return GetHttps(httpsUrl);
    }
    public JSONObject getLifeSuggestion(String location){
        httpsUrl = urlRoot + urlFileLifeSuggestion + urlkey + urlLocation + location + urlLanguage;
        return GetHttps(httpsUrl);
    }
    public JSONObject getLocationSearch(String location){
        httpsUrl = urlRoot + urlFileLocationSearch + urlkey + urlQ + location;
        return GetHttps(httpsUrl);
    }

    /***
     * 获取数据
     * @param httpsUrl url全地址
     * @return Json
     */
    private JSONObject GetHttps(String httpsUrl){
        BufferedReader reader = null;
        JSONObject result = null;
        StringBuffer sbf = new StringBuffer();
        try{
            URL url = new URL(httpsUrl);
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new MyHostnameVerifier());
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream is = null;
            switch (conn.getResponseCode()) {
                case HttpsURLConnection.HTTP_NOT_FOUND:
                    is = conn.getErrorStream();
                    break;
                case HttpURLConnection.HTTP_OK:
                    is = conn.getInputStream();
                    break;
            }

            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            String str = null;
            str = sbf.toString();
            result =  new JSONObject(str);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;

    }
    private class MyHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            // TODO Auto-generated method stub
            return true;
        }
    }

    private class MyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
