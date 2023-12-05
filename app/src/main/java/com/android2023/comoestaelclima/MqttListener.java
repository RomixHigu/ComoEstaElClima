package com.android2023.comoestaelclima;

public interface MqttListener {
    void onMessageReceived(String message);
}