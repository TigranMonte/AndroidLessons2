package ru.startandroid.p1451matrixtransform2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
        Path path;
        Path pathDst;
        RectF rectfBounds;
        RectF rectfDst;
        Matrix matrix;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            rectfDst = new RectF();
            rectfBounds = new RectF();

            path = new Path();
            path.addCircle(200, 100, 50, Path.Direction.CW);
            path.addCircle(200, 225, 75, Path.Direction.CW);
            path.addCircle(200, 400, 100, Path.Direction.CW);

            pathDst = new Path();
            matrix = new Matrix();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80,102, 204, 255);

            rectfDst.set(500, 50, 800, 150);

            // снеговик
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);

            // граница снеговика
            path.computeBounds(rectfBounds, true);
            p.setColor(Color.GREEN);
            canvas.drawRect(rectfBounds, p);

            // START
            // рамка
            p.setColor(Color.BLACK);
            canvas.drawRect(rectfDst, p);
            // преобразование
            matrix.reset();
            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.START);
            path.transform(matrix, pathDst);
            // снеговик
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);

            rectfDst.offset(0, 150);

            // CENTER
            // рамка
            p.setColor(Color.BLACK);
            canvas.drawRect(rectfDst, p);
            // преобразование
            matrix.reset();
            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.CENTER);
            path.transform(matrix, pathDst);
            // снеговик
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);

            rectfDst.offset(0,150);

            // END
            // рамка
            p.setColor(Color.BLACK);
            canvas.drawRect(rectfDst, p);
            // преобразование
            matrix.reset();
            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.END);
            path.transform(matrix, pathDst);
            // снеговик
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);

            rectfDst.offset(0,150);

            // FILL
            // рамка
            p.setColor(Color.BLACK);
            canvas.drawRect(rectfDst, p);
            // преобразование
            matrix.reset();
            matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.FILL);
            path.transform(matrix, pathDst);
            // снеговик
            p.setColor(Color.BLUE);
            canvas.drawPath(pathDst, p);
        }
    }
}