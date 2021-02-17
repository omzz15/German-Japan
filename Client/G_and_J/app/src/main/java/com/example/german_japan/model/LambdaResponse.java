package com.example.german_japan.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LambdaResponse {
    private int statusCode;
    private String body;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getBodyObj(Class type) {
        Gson gson = new Gson();
        return gson.fromJson(body, type);
    }
}
