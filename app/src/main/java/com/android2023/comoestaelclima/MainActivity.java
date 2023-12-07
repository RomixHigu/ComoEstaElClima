package com.android2023.comoestaelclima;

import static com.android2023.comoestaelclima.R.drawable.sunny;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Mqtt mqttManager;
    TextView datoT,datoH;
    ImageView background;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        background = findViewById(R.id.imgBG);
        datoT = findViewById(R.id.txtT);
        datoH = findViewById(R.id.txtH);
        mqttManager = new Mqtt(getApplicationContext());
        mqttManager.connectToMqttBroker();

        // Recibir Mensaje de MQTT
        mqttManager.setMessageReceivedListener(new Mqtt.MessageReceivedListener() {
            @Override
            public void onMessageReceived(String message) {
                String[] mensajeRec = new ArrayList<>().toArray(new String[0]);
                mensajeRec = message.split("-");
                String t = mensajeRec[0];
                String h = mensajeRec[1];
                datoT.setText(t + "°C");
                datoH.setText(h + "%");
                diaSoleado(t);
            }
        });
    }

    //Compara temperatura y cambia fondo

    void diaSoleado(String dato){
        TextView mensaje, recibir;
        mensaje = findViewById(R.id.textView6);
        recibir = findViewById(R.id.txtRecibir);
        float temp = Float.parseFloat(dato);
        Context context = this;
        if (temp > 21.00){
            recibir.setText("");
            mensaje.setText("El clima está ideal para lavar ropa");
            background.setImageResource(sunny);
        }else{
            recibir.setText("");
            mensaje.setText("Por hoy es mejor no lavar");
            background.setImageResource(R.drawable.rainy);
        }
    }
}