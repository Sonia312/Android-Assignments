package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Creating a Bitmap
        Bitmap bg = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
//Setting the Bitmap as background for the ImageView
        ImageView im = (ImageView) findViewById(R.id.imageView);
        im.setBackgroundDrawable(new BitmapDrawable(bg));
//Creating the Canvas Object
        Canvas canvas = new Canvas(bg);
//Creating the Paint Object and set its color & TextSize
        Paint paint = new Paint();

        paint.setColor(Color.BLUE);
        paint.setTextSize(50);

        Intent receiverIntent = getIntent();

        ArrayList<String> dataLabels = receiverIntent.getStringArrayListExtra("dataLabels");
        ArrayList<String> dataValues = receiverIntent.getStringArrayListExtra("dataValues");

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int radius = Math.min(width, height) / 2;

        // Center of the pie chart
        int centerX = width / 2;
        int centerY = height / 2;

        paint.setAntiAlias(true);

        float startAngle = 0;
        float total = 0;

        for (String value : dataValues) {
            total += Float.parseFloat(value);
        }

        for (int i = 0; i < dataValues.size(); i++) {
            float sweepAngle = (Float.parseFloat(dataValues.get(i)) / total) * 360;

            paint.setColor(Color.parseColor("#" + Integer.toHexString(0xFF000000 | (i * 40) + 0x00FFFF)));
            canvas.drawArc(
                    centerX - radius,
                    centerY - radius,
                    centerX + radius,
                    centerY + radius,
                    startAngle,
                    sweepAngle,
                    true,
                    paint
            );

            startAngle += sweepAngle;

            // Draw label
            float labelRadius = radius * 0.7f;
            float labelX = (float) (centerX + labelRadius * Math.cos(Math.toRadians(startAngle - sweepAngle / 2)));
            float labelY = (float) (centerY + labelRadius * Math.sin(Math.toRadians(startAngle - sweepAngle / 2)));

            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            canvas.drawText(dataLabels.get(i), labelX, labelY, paint);
        }
    }
}