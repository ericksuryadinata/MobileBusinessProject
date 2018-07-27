package com.erick.uas.loopj;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by congf on 04/09/2017.
 */

public class Request {

    private static String url = "e-learning.vultrateam.com";
    private static final String BASE_URL = "http://"+url+"/index.php/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
        Log.d("alamat", "get: "+getAbsoluteUrl(url));

    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(600000);
        client.post(getAbsoluteUrl(url), params, responseHandler);
        Log.d("alamat", "post: "+getAbsoluteUrl(url));
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
