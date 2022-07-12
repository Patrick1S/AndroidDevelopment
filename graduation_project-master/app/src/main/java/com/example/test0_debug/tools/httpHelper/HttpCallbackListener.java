package com.example.test0_debug.tools.httpHelper;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
