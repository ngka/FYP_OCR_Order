package com.example.fyp_ocr_order;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeePage_Ng extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeehome_ng);
        setTitle("Employee");
        Button Data1 = findViewById(R.id.Data1);
        Button ocr = findViewById(R.id.ocr);
        Button cnn = findViewById(R.id.cnn);
        Button Query = findViewById(R.id.Query);
        Button Data = findViewById(R.id.Data);

        ocr.setOnClickListener(view -> {
            Intent intent = new Intent(EmployeePage_Ng.this, OCR_Ng.class);
            startActivity(intent);
        });
        cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeePage_Ng.this, CNN_Ng.class);
                startActivity(intent);
            }
        });

        Query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeePage_Ng.this, Query_Ng.class);
                startActivity(intent);
            }
        });

        Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeePage_Ng.this, Employee_Query_Ng.class);
                startActivity(intent);
            }
        });
        Data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeePage_Ng.this, Employee_Urgent_Ng2.class);
                startActivity(intent);
            }
        });


    }

}
