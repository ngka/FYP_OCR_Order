package com.example.fyp_ocr_order;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Employee_Query_Ng extends AppCompatActivity {
    TextView textView;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_query_ng);
        setTitle("Query Question for Employee");

        button = findViewById(R.id.submit);
        textView = findViewById(R.id.textView);
        EditText edit_Username = findViewById(R.id.Employee_Name);
        EditText edit_STATUS = findViewById(R.id.STATUS);
        EditText edit_REGION = findViewById(R.id.REGION);

        button.setOnClickListener(view -> {
            String Employee_Name = edit_Username.getText().toString();
            String STATUS = edit_STATUS.getText().toString();
            String REGION = edit_REGION.getText().toString();

            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://192.168.56.49/FYP/FYP_websiteData/User_Website_workVersion/Employee_get_data.php?Employee_Name=" + Employee_Name + "&STATUS=" + STATUS + "&REGION=" +REGION;

            StringRequest myReq = new StringRequest(Request.Method.GET, url,
                    response -> {
                        if (!response.equals("No data found")) {
                            String question = response.trim();
                            textView.setText(question);
                        } else {
                            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> Log.e("Error", error.getLocalizedMessage()));
            queue.add(myReq);
        });
    }
}

