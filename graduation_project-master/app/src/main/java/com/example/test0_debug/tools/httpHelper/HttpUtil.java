package com.example.test0_debug.tools.httpHelper;


import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(address).build();
            try {
                Response response = client.newCall(request).execute();
                String responseData = Objects.requireNonNull(response.body()).string();
                if (listener != null) {
                    listener.onFinish(responseData);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.onError(e);
                }
            }
        }).start();
    }
}
