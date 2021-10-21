package ru.startandroid.p1111preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;


public class Fragment1 extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref1);
    }
}
