package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button b1, b2;
    EditText t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        b1 = (Button) findViewById(R.id.button18);
        b2 = (Button) findViewById(R.id.button21);
        t1 = (EditText) findViewById(R.id.textView3);
        t2 = (EditText) findViewById(R.id.textView6);

        ArrayList<String> dataLabels = new ArrayList<>();
        ArrayList<String> dataValues = new ArrayList<>();

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dataLabels.add(t1.getText().toString());
                dataValues.add(t2.getText().toString());
                t1.setText("");
                t2.setText("");
                System.out.println(dataLabels);
                System.out.println(dataValues);
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent senderIntent = new Intent(MainActivity2.this, MainActivity.class);
                senderIntent.putExtra("dataValues", dataValues);
                senderIntent.putExtra("dataLabels", dataLabels);
                startActivity(senderIntent);
            }
        });

    }
}