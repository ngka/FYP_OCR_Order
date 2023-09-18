package com.example.fyp_ocr_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeePage_Ng extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeehome_ng);
        setTitle("Employee");
        ImageView Data1 = (ImageView) findViewById(R.id.Data1);
        ImageView Data = (ImageView) findViewById(R.id.Data);
        ImageView ocr = (ImageView) findViewById(R.id.ocr);
        ImageView Query = (ImageView) findViewById(R.id.Query);
        ImageView Update = (ImageView) findViewById(R.id.Update);


        ocr.setOnClickListener(view -> {
            Intent intent = new Intent(EmployeePage_Ng.this, OCR_Ng.class);
            startActivity(intent);
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

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeePage_Ng.this, EmployeeUpdate_Ng.class);
                startActivity(intent);
            }
        });






    }

}
