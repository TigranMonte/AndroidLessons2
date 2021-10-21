package ru.startandroid.p1111preferencefragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import java.util.List;

public class MainActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_head, target);
    }

    protected boolean isValidFragment (String fragmentName){
      return true;
    }

    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);  не используем layout

        //getFragmentManager().beginTransaction()
                //.replace(android.R.id.content, new Fragment1()).commit();
    //}


}