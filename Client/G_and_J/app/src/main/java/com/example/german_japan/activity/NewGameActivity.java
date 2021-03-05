package com.example.german_japan.activity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.example.german_japan.R;
import com.example.german_japan.model.AppModels;
import com.example.german_japan.model.Client;
import com.example.german_japan.model.GameSettings;
import com.example.german_japan.model.LambdaRequest;
import com.example.german_japan.model.LambdaResponse;
import com.example.german_japan.service.CreateGame;
import com.example.german_japan.util.ViewManager;


public class NewGameActivity extends AppCompatActivity {

    Client client = AppModels.getClient(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);
        ViewManager.GameSettings.SpinnerManager.ActivationSequence(this, client);
    }



    public void onCreateGamePressed(View view)
    {
        GameSettings gs = new GameSettings();
        Object[] vals = ViewManager.SpinnerManager.getGroupValues(ViewManager.GameSettings.SpinnerManager.get());
        gs.setPlayers((Integer) vals[0]);
        gs.setDeckCount((Integer) vals[1]);
        gs.setStartingCards((Integer) vals[2]);

        client.setGameSettings(gs);

        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
                this.getApplicationContext(), "us-west-2:59477c2f-8f43-4984-948f-8dcc4757dd47", Regions.US_WEST_2);

        // Create LambdaInvokerFactory, to be used to instantiate the Lambda proxy.
        LambdaInvokerFactory factory = new LambdaInvokerFactory(this.getApplicationContext(),
                Regions.US_WEST_2, cognitoProvider);

        final CreateGame myInterface = factory.build(CreateGame.class);
        LambdaRequest request = new LambdaRequest();
        request.setBody(client);

        new AsyncTask<LambdaRequest, Void, LambdaResponse>() {
            @Override
            protected LambdaResponse doInBackground(LambdaRequest... params) {
                // invoke "echo" method. In case it fails, it will throw a
                // LambdaFunctionException.
                try {
                    return myInterface.createGame(params[0]);
                } catch (LambdaFunctionException lfe) {
                    //Log.e("Tag", "Failed to invoke echo", lfe);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(LambdaResponse result) {
                AppModels.setClient(getApplicationContext(), (Client) result.getBodyObj(Client.class));
            }
        }.execute(request);

    }
}
