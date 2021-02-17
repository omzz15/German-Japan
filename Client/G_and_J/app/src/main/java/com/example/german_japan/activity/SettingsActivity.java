package com.example.german_japan.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import com.example.german_japan.R;
import com.example.german_japan.model.AppModels;
import com.example.german_japan.model.Client;
import com.example.german_japan.model.GameSettings;
import com.example.german_japan.util.ViewManager;
import com.google.android.material.tabs.TabLayout;

public class SettingsActivity extends AppCompatActivity {

    TabLayout settingTypeSelector;
    Group gameSettingsGroup;
    Group visualSettingsGroup;
    Group otherSettingsGroup;

    Client client = AppModels.getClient(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        setAllViews();
        settingTypeSelector.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                settingTypeChanged(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    public void settingTypeChanged(TabLayout.Tab tab)
    {
        if(tab.getPosition() == 0)
        {
            setGroupVisibility(gameSettingsGroup, View.VISIBLE);
            setGroupVisibility(visualSettingsGroup, View.INVISIBLE);
            setGroupVisibility(otherSettingsGroup, View.INVISIBLE);
        }
        if(tab.getPosition() == 1)
        {
            setGroupVisibility(gameSettingsGroup, View.INVISIBLE);
            setGroupVisibility(visualSettingsGroup, View.VISIBLE);
            setGroupVisibility(otherSettingsGroup, View.INVISIBLE);
        }
        if(tab.getPosition() == 2)
        {
            setGroupVisibility(gameSettingsGroup, View.INVISIBLE);
            setGroupVisibility(visualSettingsGroup, View.INVISIBLE);
            setGroupVisibility(otherSettingsGroup, View.VISIBLE);
        }
    }

    private void setGroupVisibility(Group group, int visibility)
    {
        group.setVisibility(View.GONE);
        group.setVisibility(visibility);
    }

    private void setAllViews()
    {
        settingTypeSelector = findViewById(R.id.SettingTabs);
        gameSettingsGroup = findViewById(R.id.GameSettingsGroup);
        visualSettingsGroup = findViewById(R.id.VisualSettingGroup);
        otherSettingsGroup = findViewById(R.id.OtherSettingGroup);

        ViewManager.SpinnerManager.GameSettings.ActivationSequence(this, client);
    }

    public void onTest(View view)
    {
        System.out.println("test");
    }
}
