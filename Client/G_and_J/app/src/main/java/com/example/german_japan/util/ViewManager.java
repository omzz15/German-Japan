package com.example.german_japan.util;


import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.german_japan.R;
import com.example.german_japan.model.Client;

import java.util.ArrayList;
import java.util.Collections;

public class ViewManager {
    public static class GroupManager{}

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
    }

    public static class GameSettings {
        public static class SpinnerManager extends ViewManager.SpinnerManager {
            private static Spinner[] spinners = new Spinner[4];

            public static void set(AppCompatActivity appCompatActivity) {
                spinners[0] = (appCompatActivity.findViewById(R.id.PlayersInput));
                spinners[1] = (appCompatActivity.findViewById(R.id.NumOfDecksInput));
                spinners[2] = (appCompatActivity.findViewById(R.id.StartingCardsInput));
                spinners[3] = appCompatActivity.findViewById(R.id.SequencesRequiredInput);
            }

            public static void set(Spinner[] gameSettingSpinners) {
                SpinnerManager.spinners = gameSettingSpinners;
            }

            public static Spinner[] get() {
                return spinners;
            }

            public static void setValues(AppCompatActivity appCompatActivity) {
                SpinnerManager.setValues(spinners[0], appCompatActivity, 2, 8, 2, new Integer[]{3});
                SpinnerManager.setValues(spinners[1], appCompatActivity, 1, 4, 1, null);
                SpinnerManager.setValues(spinners[2], appCompatActivity, 13, 17, 2, null);
                SpinnerManager.setValues(spinners[3], appCompatActivity, 1, 5, 1, null);
            }

            public static void setCurrentPos(Client client) {
                setCurrentValue(spinners[0], client.getGameSettings().getPlayers());
                setCurrentValue(spinners[1], client.getGameSettings().getDeckCount());
                setCurrentValue(spinners[2], client.getGameSettings().getStartingCards());
                setCurrentValue(spinners[3], client.getGameSettings().getSequencesRequired());
            }

            public static void ActivationSequence(AppCompatActivity appCompatActivity, Client client) {
                set(appCompatActivity);
                setValues(appCompatActivity);
                setCurrentPos(client);
            }
        }
    }
}
