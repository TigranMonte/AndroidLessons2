package ru.startandroid.p1431drawingpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }
    class DrawView extends View {

        Paint p;
        RectF rectF;
        Path path;
        Path path1;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            rectF = new RectF(350, 100, 400, 150);
            path = new Path();
            path1 = new Path();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            //  очистка path
            path.reset();

            // угол
            path.moveTo(100, 100);
            path.lineTo(150, 200);
            path.lineTo(50, 200);

            // треугольник
            path.moveTo(250, 100);
            path.lineTo(300, 200);
            path.lineTo(200, 200);
            path.close();

            // квадрат и круг
            path.addRect(rectF, Path.Direction.CW);
            path.addCircle(450, 150, 25, Path.Direction.CW);

            // рисование path
            p.setColor(Color.BLACK);
            canvas.drawPath(path, p);

            // очистка path1
            path1.reset();

            // две пересекающиеся линии
            path1.moveTo(50, 50);
            path1.lineTo(500, 250);
            path1.moveTo(500, 50);
            path1.lineTo(50, 250);

            // рисование path1
            p.setColor(Color.GREEN);
            canvas.drawPath(path1, p);

            // добавление path1 к oath
            path.addPath(path1);

            // смещение
            path.offset(500, 100);

            // рисование path
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);
        }
    }
}