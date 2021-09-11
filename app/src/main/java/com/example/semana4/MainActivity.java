package com.example.semana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button searchButton, pingButton;
    Socket socket;

    BufferedReader reader;
    BufferedWriter writer;

    TextView ipShowcaser;
    EditText ip1, ip2, ip3, ip4;

    String ipAddress;
    int ip1TEXT, ip2TEXT, ip3TEXT, ip4TEXT;

    boolean isPinging;

    InetAddress ipCommon, ipDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.searchBtn);
        pingButton = findViewById(R.id.pingBtn);
        ipShowcaser = findViewById(R.id.ipShow);

        ip1 = findViewById(R.id.IPet1);
        ip2 = findViewById(R.id.IPet2);
        ip3 = findViewById(R.id.IPet3);
        ip4 = findViewById(R.id.IPet4);

        initClient();

        searchButton.setOnClickListener(
                (view) ->
                {
                    isPinging = false;

                    ip4TEXT = 0;
                    moveToHostScreen();
                }
        );

        pingButton.setOnClickListener(
                (view) ->
                {
                    isPinging = true;

                    ip1TEXT = Integer.parseInt(ip1.getText().toString());
                    ip2TEXT = Integer.parseInt(ip2.getText().toString());
                    ip3TEXT = Integer.parseInt(ip3.getText().toString());
                    ip4TEXT = Integer.parseInt(ip4.getText().toString());

                    ipAddress = ip1TEXT + "." + ip2TEXT + "." + ip3TEXT + "." + ip4TEXT;

                    if(ip1TEXT < 0 || ip1TEXT > 255)
                    {
                        Toast.makeText(this, "Inserte números entre 0 y 255", Toast.LENGTH_SHORT);
                    }
                    if(ip2TEXT < 0 || ip2TEXT > 255)
                    {
                        Toast.makeText(this, "Inserte números entre 0 y 255", Toast.LENGTH_SHORT);
                    }
                    if(ip3TEXT < 0 || ip3TEXT > 255)
                    {
                        Toast.makeText(this, "Inserte números entre 0 y 255", Toast.LENGTH_SHORT);
                    }
                    if(ip4TEXT < 0 || ip4TEXT > 255)
                    {
                        Toast.makeText(this, "Inserte números entre 0 y 255", Toast.LENGTH_SHORT);
                    }

                    ping(ipAddress);

                    Log.d("ip", String.valueOf(ipAddress));
                }
        );
    }

    public void initClient()
    {
        new Thread(
                () ->
                {
                    try {
                        ipCommon = InetAddress.getLocalHost();
                        runOnUiThread(
                                ()->
                                {
                                    ipShowcaser.setText(ipCommon.toString());
                                }
                        );
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    public void moveToHostScreen()
    {
        Intent switchActivity = new Intent(this, HostSearchScreen.class);
        switchActivity.putExtra("hostSearcher1", ip1.getText().toString());
        switchActivity.putExtra("hostSearcher2", ip2.getText().toString());
        switchActivity.putExtra("hostSearcher3", ip3.getText().toString());
        switchActivity.putExtra("hostSearcher4", ip4.getText().toString());
        switchActivity.putExtra("pingBool", isPinging);
        startActivity(switchActivity);
    }

    public void ping(String ipDestination)
    {
        Intent i = new Intent(this, HostSearchScreen.class);
        i.putExtra("ipDestination", ipDestination);
        i.putExtra("pingBool", isPinging);
        startActivity(i);
    }
}