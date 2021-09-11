package com.example.semana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostSearchScreen extends AppCompatActivity {

    Button returnBtn;
    TextView showcaser;
    String ipDestination, hostSearchText;

    String ipHost1, ipHost2, ipHost3, ipHost4;

    boolean isPing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_search_screen);

        hostSearchText = "";

        returnBtn = findViewById(R.id.returnButton);
        showcaser = findViewById(R.id.ipHostsField);
        ipDestination = getIntent().getStringExtra("ipDestination");
        isPing = getIntent().getBooleanExtra("pingBool", false);

        ipHost1 = getIntent().getStringExtra("hostSearcher1");
        ipHost2 = getIntent().getStringExtra("hostSearcher2");
        ipHost3 = getIntent().getStringExtra("hostSearcher3");
        ipHost4 = getIntent().getStringExtra("hostSearcher4");


        returnBtn.setOnClickListener(
                (view) ->
                {
                    returnToMainScreen();
                }
        );

        new Thread(
                () ->
                {
                    if (isPing)
                    {
                        for (int i = 0; i < 5; i++) {
                            try {
                                InetAddress ipSearch = InetAddress.getByName(ipDestination);

                                if (ipSearch.isReachable(500)) {
                                    hostSearchText += "PING recibido\n";
                                } else {
                                    hostSearchText += "PING perdido\n";
                                }

                                runOnUiThread(
                                        () ->
                                        {
                                            showcaser.setText(hostSearchText);
                                        }
                                );

                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    else
                    {
                            try {
                                for(int i = 0; i <= 255; i++)
                                {
                                    ipDestination = ipHost1 + "." + ipHost2 + "." + ipHost3 + "." + i;
                                    InetAddress ipSearch = InetAddress.getByName(ipDestination);

                                    if (ipSearch.isReachable(500)) {
                                        hostSearchText += ipDestination + "\n";
                                    }
                                    else
                                    {

                                    }

                                    runOnUiThread(
                                            () ->
                                            {
                                                showcaser.setText(hostSearchText);
                                            }
                                    );
                                }

                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                }
        ).start();
    }

    public void returnToMainScreen()
    {
        Intent switchActivity = new Intent(this, MainActivity.class);
        startActivity(switchActivity);
    }
}