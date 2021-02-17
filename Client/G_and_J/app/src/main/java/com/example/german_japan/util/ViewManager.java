package com.example.german_japan.util;


import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.german_japan.R;
import com.example.german_japan.model.Client;
import java.util.ArrayList;
import java.util.Collections;


public class ViewManager {
    private static ViewManager instance;
    public SpinnerManager spinnerManager = new SpinnerManager();

    public static ViewManager getInstance()
    {
        if(instance == null)instance = new ViewManager();
        return instance;
    }


    public class SpinnerManager extends ViewManager{
        public GameSettings gameSettings = new GameSettings();

        ////////////////////
        //get group values//
        ////////////////////
        public Object[] getGroupValues(Spinner[] spinners)
        {
            Object[] vals = new Object[spinners.length];
            for(int i = 0; i < spinners.length; i++)
            {
                vals[i] = spinners[i].getSelectedItem();
            }
            return vals;
        }

        /////////
        //other//
        /////////
        public void setValues(Spinner spinner, AppCompatActivity appCompatActivity, int start, int end, int step, Integer[] otherVals) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for(Integer i = start; i <= end; i+=step)
            {
                arrayList.add(i);
            }
            if(otherVals != null)
            {
                for(int i = 0; i < otherVals.length; i++)
                {
                    arrayList.add(otherVals[i]);
                }
            }
            Collections.sort(arrayList);
            ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(appCompatActivity, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }

        public void setCurrentPos(Spinner spinner, Integer pos)
        {
            spinner.post(new Runnable() {
                public void run() {
                    spinner.setSelection(pos);
                }
            });
        }

        public void setCurrentValue(Spinner spinner, Object value)
        {
            setCurrentPos(spinner, getIndex(spinner, value));
        }

        public int getIndex(Spinner spinner, Object val) {
            for (int i = 0; i < spinner.getCount(); i++) {
                if (spinner.getItemAtPosition(i) == val) {
                    return i;
                }
            }
            return 0;
        }

        
        public  class GameSettings extends SpinnerManager
        {
            private Spinner[] gameSettingSpinners =  new Spinner[3];

            public void set(AppCompatActivity appCompatActivity)
            {
                gameSettingSpinners[0] = (appCompatActivity.findViewById(R.id.PlayersInput));
                gameSettingSpinners[1] = (appCompatActivity.findViewById(R.id.NumOfDecksInput));
                gameSettingSpinners[2] = (appCompatActivity.findViewById(R.id.StartingCardsInput));
            }

            public void set(Spinner[] gameSettingSpinners) {
                this.gameSettingSpinners = gameSettingSpinners;
            }

            public Spinner[] get() {
                return gameSettingSpinners;
            }

            public void setValues(AppCompatActivity appCompatActivity)
            {
                setValues(gameSettingSpinners[0], appCompatActivity,2, 8, 2, new Integer[]{3});
                setValues(gameSettingSpinners[1], appCompatActivity,1, 4, 1, null);
                setValues(gameSettingSpinners[2], appCompatActivity,13, 17, 2, null);
            }
            public void setCurrentPos(Client client)
            {
                setCurrentValue(gameSettingSpinners[0], client.getGameSettings().getPlayers());
                setCurrentValue(gameSettingSpinners[1], client.getGameSettings().getDeckCount());
                setCurrentValue(gameSettingSpinners[2], client.getGameSettings().getStartingCards());
            }

            public void ActivationSequence(AppCompatActivity appCompatActivity, Client client)
            {
                set(appCompatActivity);
                setValues(appCompatActivity);
                setCurrentPos(client);
            }
        }
    }
}
