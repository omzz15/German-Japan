package com.example.german_japan.util;


import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.german_japan.R;
import com.example.german_japan.model.Client;

import java.util.ArrayList;
import java.util.Collections;

public class ViewManager {
    public static class SpinnerManager{
        ////////////////////
        //get group values//
        ////////////////////
        public static Object[] getGroupValues(Spinner[] spinners) {
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
        public static void setValues(Spinner spinner, AppCompatActivity appCompatActivity, int start, int end, int step, Integer[] otherVals) {
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

        public static void setCurrentPos(Spinner spinner, Integer pos) {
            spinner.post(new Runnable() {
                public void run() {
                    spinner.setSelection(pos);
                }
            });
        }

        public static void setCurrentValue(Spinner spinner, Object value) { setCurrentPos(spinner, getIndex(spinner, value)); }

        public static int getIndex(Spinner spinner, Object val) {
            for (int i = 0; i < spinner.getCount(); i++) {
                if (spinner.getItemAtPosition(i) == val) {
                    return i;
                }
            }
            return 0;
        }

        
        public static class GameSettings {
            private static Spinner[] gameSettingSpinners =  new Spinner[3];

            public static void set(AppCompatActivity appCompatActivity)
            {
                gameSettingSpinners[0] = (appCompatActivity.findViewById(R.id.PlayersInput));
                gameSettingSpinners[1] = (appCompatActivity.findViewById(R.id.NumOfDecksInput));
                gameSettingSpinners[2] = (appCompatActivity.findViewById(R.id.StartingCardsInput));
            }

            public static void set(Spinner[] gameSettingSpinners) {
                GameSettings.gameSettingSpinners = gameSettingSpinners;
            }

            public static Spinner[] get() {
                return gameSettingSpinners;
            }

            public static void setValues(AppCompatActivity appCompatActivity)
            {
                SpinnerManager.setValues(gameSettingSpinners[0], appCompatActivity,2, 8, 2, new Integer[]{3});
                SpinnerManager.setValues(gameSettingSpinners[1], appCompatActivity,1, 4, 1, null);
                SpinnerManager.setValues(gameSettingSpinners[2], appCompatActivity,13, 17, 2, null);
            }
            public static void setCurrentPos(Client client)
            {
                setCurrentValue(gameSettingSpinners[0], client.getGameSettings().getPlayers());
                setCurrentValue(gameSettingSpinners[1], client.getGameSettings().getDeckCount());
                setCurrentValue(gameSettingSpinners[2], client.getGameSettings().getStartingCards());
            }

            public static void ActivationSequence(AppCompatActivity appCompatActivity, Client client)
            {
                set(appCompatActivity);
                setValues(appCompatActivity);
                setCurrentPos(client);
            }
        }
    }
}
