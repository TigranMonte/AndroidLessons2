package ru.tikodvlp.todolist.support;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import ru.tikodvlp.todolist.R;
import ru.tikodvlp.todolist.screens.main.MainActivity;

public class SplashScreen extends AppCompatActivity {

    public static final int SPLASH_SCREEN = 3000;

    Animation topAnimation;
    ImageView ivLogo;
    TextView tvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        ivLogo = findViewById(R.id.ivLogo);
        tvLogo = findViewById(R.id.tvLogo);

        ivLogo.setAnimation(topAnimation);
        tvLogo.setAnimation(topAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}