package com.skycast.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

public class SkycastRestUtil {


    public static JSONObject httpGetJSONObject(final String url) throws IOException, JSONException {
        return new JSONObject(httpGetJSONString(url));
    }


    public static String httpGetJSONString(final String url) throws IOException {
        final HttpURLConnection httpCon = createHttpCon(url, "GET");
        final BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        
        final StringBuffer httpResponse = new StringBuffer();
        String line = "";
        while (null != (line = br.readLine())) {
            httpResponse.append(line);
        }
        return httpResponse.toString();
    }


    private static HttpURLConnection createHttpCon(final String url,final String method) throws IOException {

        final HttpURLConnection httpCon;
        httpCon = (HttpURLConnection) new URL(url).openConnection();
        
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(method);

        httpCon.setRequestProperty("Content-type", "application/json");

        return httpCon;
    }

}
