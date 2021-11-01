package ru.startandroid.p1161mngtasks1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityA extends MainActivity {

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ActivityB.class));
    }
}