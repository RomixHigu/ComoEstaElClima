package com.android2023.comoestaelclima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    private Mqtt mqttManager;
    TextView texto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        texto = findViewById(R.id.txtMensaje);
        mqttManager = new Mqtt(getApplicationContext());
        mqttManager.connectToMqttBroker();


        /*String mensajeRecibido = intent.getStringExtra("mensaje");
        texto.setText(mensajeRecibido);*/
        
    }
}