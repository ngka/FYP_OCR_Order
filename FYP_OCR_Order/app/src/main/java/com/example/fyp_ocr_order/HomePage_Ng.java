package com.example.fyp_ocr_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage_Ng extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ng);
        Button ocr = findViewById(R.id.ocr);
        Button cnn = findViewById(R.id.cnn);
        Button feedback = findViewById(R.id.feedback);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage_Ng.this, Feedback_Ng.class);
                startActivity(intent);
            }
        });
        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage_Ng.this, OCR_Ng.class);
                startActivity(intent);
            }
        });
        cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage_Ng.this, CNN_Ng.class);
                startActivity(intent);
            }
        });
    }

}
