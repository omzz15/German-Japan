package com.example.german_japan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.example.german_japan.R;
import com.example.german_japan.model.AppModels;
import com.example.german_japan.model.Client;
import com.example.german_japan.model.LambdaRequest;
import com.example.german_japan.model.LambdaResponse;
import com.example.german_japan.service.RegisterClient;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onSubmitPressed(View view)
    {
        TextView nameBox = findViewById(R.id.clientName);
        String name = nameBox.getText().toString();

        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
                this.getApplicationContext(), "us-west-2:59477c2f-8f43-4984-948f-8dcc4757dd47", Regions.US_WEST_2);

        // Create LambdaInvokerFactory, to be used to instantiate the Lambda proxy.
        LambdaInvokerFactory factory = new LambdaInvokerFactory(this.getApplicationContext(),
                Regions.US_WEST_2, cognitoProvider);

        final RegisterClient myInterface = factory.build(RegisterClient.class);
        LambdaRequest request = new LambdaRequest();
        Client cl = new Client();
        cl.setName(name);
        request.setBody(cl);
        final Context currentContext = this;
        new AsyncTask<LambdaRequest, Void, LambdaResponse>() {
            @Override
            protected LambdaResponse doInBackground(LambdaRequest... params) {
                // invoke "echo" method. In case it fails, it will throw a
                // LambdaFunctionException.
                try {
                    return myInterface.registerClient(params[0]);
                } catch (LambdaFunctionException lfe) {
                    //Log.e("Tag", "Failed to invoke echo", lfe);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(LambdaResponse result) {
                AppModels.setClient(getApplicationContext(), (Client) result.getBodyObj(Client.class));
                Intent i = new Intent(currentContext, MainActivity.class);
                startActivity(i);
            }
        }.execute(request);
    }

}
