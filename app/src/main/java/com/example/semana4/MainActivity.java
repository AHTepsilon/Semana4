package com.example.semana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    String ipAddress, ip1TEXT, ip2TEXT, ip3TEXT, ip4TEXT;

    InetAddress ipCommon;

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
                    moveToHostScreen();
                }
        );

        pingButton.setOnClickListener(
                (view) ->
                {
                    ip1TEXT = ip1.getText().toString();
                    ip2TEXT = ip2.getText().toString();
                    ip3TEXT = ip3.getText().toString();
                    ip4TEXT = ip4.getText().toString();

                    ipAddress = ip1TEXT + "." + ip2TEXT + "." + ip3TEXT + "." + ip4TEXT;
                    Log.d("ip", ipAddress);
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
                        ipShowcaser.setText(ipCommon.toString());
                        socket = new Socket("192.168.1.9", 4000);

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        reader = new BufferedReader(isr);

                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        writer = new BufferedWriter(osw);

                        while(true)
                        {
                            System.out.println("Awaiting message...");
                            String line = reader.readLine();
                            System.out.println("Received message: " + line);
                        }

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
        startActivity(switchActivity);
    }

    public void ping()
    {

    }
}