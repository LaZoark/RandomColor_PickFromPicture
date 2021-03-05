package com.example.randomcolorgen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView redCode,greenCode,blueCode;
//    TextView mResultTV;
    Button showAndRandomButton;
    private int r,g,b;      // to store color values
    Bitmap bitmap;
    class MyOnClickListener implements View.OnTouchListener {
        @SuppressLint("SetTextI18n")
//        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                bitmap = mImageView.getDrawingCache();

                int pixel = bitmap.getPixel((int) event.getX(), (int) event.getY());
                // getting RGB values
                r = Color.red(pixel);
                g = Color.green(pixel);
                b = Color.blue(pixel);

                //getting Hex values
                String hex = "#" + Integer.toHexString(pixel);

                //set background color of view according to the picked color
                showAndRandomButton.setBackgroundColor(Color.rgb(r, g, b));
                //set RGB, Hec values to textView
                //                    mResultTV.setText("RGB: "+ r +", "+ g +", "+ b + "\nHEX: "+ hex);
                redCode.setText("R： " + r);
                greenCode.setText("G： " + g);
                blueCode.setText("B： " + b);
                showAndRandomButton.setText("Random!\n" + hex);
            }
            return true;
        }
    }
    View.OnTouchListener myListener = new MyOnClickListener();  //建立監聽物件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mResultTV = findViewById(R.id.textView_R);
        redCode = findViewById(R.id.textView_R);
        greenCode = findViewById(R.id.textView_G);
        blueCode = findViewById(R.id.textView_B);

        showAndRandomButton = findViewById(R.id.button);
        mImageView = findViewById(R.id.imageView);
        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache(true);

        //image view on touch listener
        mImageView.setOnTouchListener(myListener);

    }
    public void randomColor(View v){
        Random x = new Random();
        r = x.nextInt(256);
        g = x.nextInt(256);
        b = x.nextInt(256);
        redCode.setText("R： "+r);
        greenCode.setText("G： "+g);
        blueCode.setText("B： "+b);
        showAndRandomButton.setBackgroundColor(Color.rgb(r,g,b));

        String hex = "#"+ Integer.toHexString(Color.rgb(r,g,b));
        showAndRandomButton.setText("Random!\n"+hex);
    }
}