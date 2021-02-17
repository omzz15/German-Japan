package com.example.german_japan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.german_japan.R;
import com.example.german_japan.model.AppModels;
import com.example.german_japan.model.Client;
import com.example.german_japan.util.FileManager;


public class MainActivity extends AppCompatActivity {

    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = AppModels.getClient(this);
        if (client == null) {
            System.out.println("hi");
           Intent i = new Intent(this, RegisterActivity.class);
           startActivity(i);
        }
        client = AppModels.getClient(this);
        setContentView(R.layout.activity_main);
        TextView playerId = findViewById(R.id.PlayerInfo);

        playerId.setText("Player Name: " + client.getName() + "\n" + "Player ID: " + client.getId());
    }

    public void onNewGameClicked(View view)
    {
        Intent i = new Intent(this, NewGameActivity.class);
        startActivity(i);
    }
    public void onJoinGameClicked(View view)
    {
        System.out.println("button clicked");
    }
    public void onSettingsClicked(View view)
    {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
}