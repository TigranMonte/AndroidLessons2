package ru.startandroid.p1441matrixtransform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
        Path path;
        Matrix matrix;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            path = new Path();
            matrix = new Matrix();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            //  создаем крест в path
            path.reset();
            path.addRect(300, 150, 450, 200, Path.Direction.CW);
            path.addRect(350, 100, 400, 250, Path.Direction.CW);

            // рисуем path зеленым
            p.setColor(Color.GREEN);
            canvas.drawPath(path, p);

            // настраиваем матрицу на изменение размера: в 2 раза по гориз. в 2.5 раза по верт. относительно точки (375, 100)
            matrix.reset();
            matrix.setScale(2f, 2.5f, 375, 100);

            // применяем матрицу к path
            path.transform(matrix);

            // рисуем path синим
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);

            // рисуем точку относительно которой было выполнено преображение
            p.setColor(Color.BLACK);
            canvas.drawCircle(375, 100, 5, p);
        }
    }
}