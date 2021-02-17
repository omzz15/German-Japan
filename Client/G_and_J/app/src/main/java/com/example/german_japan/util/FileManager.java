package com.example.german_japan.util;

import android.content.Context;
import android.util.Log;

import com.example.german_japan.model.Client;
import com.example.german_japan.model.CurrentGameSettings;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;

public class FileManager {
    private static final String clientFileName = "Client.txt";
    private static final String currentGameFileName = "Current Game.txt";

    private static FileManager instance = null;

    private FileManager() {
    }

    public static FileManager getInstance() {
        if (instance == null)
            instance  = new FileManager();
        return instance;
    }

    public void writeClient(Client client, Context context)
    {
        Gson g = new Gson();
        writeToFile(clientFileName, g.toJson(client), context);
    }

    public void writeCurrentGameSettings(CurrentGameSettings currentGameSettings, Context context)
    {
        Gson g = new Gson();
        if(currentGameSettings == null){writeToFile(currentGameFileName, "", context);}
        writeToFile(currentGameFileName, g.toJson(currentGameSettings), context);
    }

    public Client readClient(Context context)
    {
        Gson g = new Gson();
        String file = readFromFile(context, clientFileName);
        if(file == null) return null;
        return g.fromJson(file, Client.class);
    }

    public CurrentGameSettings readCurrentGameSettings(Context context)
    {
        Gson g = new Gson();
        String file = readFromFile(context, clientFileName);
        if(file == null || file == "") return null;
        return g.fromJson(file, CurrentGameSettings.class);
    }

    private void writeToFile(String fileName, String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context, String fileName) {

        String ret = null;

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
