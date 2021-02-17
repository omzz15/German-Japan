package com.example.german_japan.model;

import android.content.Context;

import com.example.german_japan.util.FileManager;

public class AppModels {
    private static Client client;

    public static Client getClient(Context ctx) {
        if (client == null) {
            client = FileManager.getInstance().readClient(ctx);
        }
        return client;
    }

    public static void setClient(Context ctx, Client client) {
        if (client != null) {
            FileManager.getInstance().writeClient(client, ctx);
            AppModels.client = client;
        }
    }
}
