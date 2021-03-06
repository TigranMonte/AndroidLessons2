package ru.startandroid.p1021touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    TextView tv;
    float x;
    float y;

    String sDown;
    String sUp;
    String sMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TextView(this);
        tv.setOnTouchListener(this);
        setContentView(tv);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                sDown = "Down = " + x + ", " + y;
                sMove = ""; sUp = "";
                break;
            case MotionEvent.ACTION_MOVE: // движение
                sMove = "Down = " + x + ", " + y;
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up = " + x + ", " + y;
                break;
        }

        tv.setText(sDown + "\n" + sMove + "\n" + sUp);
        return true;
    }

}