package com.example.actividad9;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements IObserver{

    private ImageButton hotdogBtn, sandwichBtn, juiceBtn, icecreamBtn;
    private CommunicationUDP udp;
    private Gson gson;
    private Order orderMessage;
    private LocalTime time;
    private DateTimeFormatter timeFormatter;
    private String json;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Elements
        hotdogBtn = findViewById(R.id.hotdogBtn);
        sandwichBtn = findViewById(R.id.sandwichBtn);
        juiceBtn = findViewById(R.id.juiceBtn);
        icecreamBtn = findViewById(R.id.icecreamBtn);

        //Classes
        udp = new CommunicationUDP(this);
        udp.start();
        gson = new Gson();
        orderMessage = new Order();

        //Buttons
        hotdogBtn.setOnClickListener((v)->{
            //Set message to know which image goes in eclipse
            orderMessage.setProduct("hot dog");
            settingTime();
            json = gson.toJson(orderMessage);
            udp.sendMessage(json);
        });

        sandwichBtn.setOnClickListener((v)->{
            //Set message to know which image goes in eclipse
            orderMessage.setProduct("sandwich");
            settingTime();
            json = gson.toJson(orderMessage);
            udp.sendMessage(json);
        });

        juiceBtn.setOnClickListener((v)->{
            //Set message to know which image goes in eclipse
            orderMessage.setProduct("juice");
            settingTime();
            json = gson.toJson(orderMessage);
            udp.sendMessage(json);
        });

        icecreamBtn.setOnClickListener((v)->{
            //Set message to know which image goes in eclipse
            orderMessage.setProduct("ice cream");
            settingTime();
            json = gson.toJson(orderMessage);
            udp.sendMessage(json);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void settingTime(){
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        time = LocalTime.now();
        orderMessage.setTime(timeFormatter.format(time));
        System.out.println(timeFormatter.format(time));
    }

    @Override
    public void notifyMessage(String message) {
        runOnUiThread(()->{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }
}