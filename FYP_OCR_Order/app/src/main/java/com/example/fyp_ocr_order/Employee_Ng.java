package com.example.fyp_ocr_order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Employee_Ng extends AppCompatActivity {
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_ng);

        button = findViewById(R.id.submit);
        EditText editText1 = findViewById(R.id.value1); //Employee_Name
        EditText editText2 = findViewById(R.id.value2); //ORDER_NO
        EditText editText3 = findViewById(R.id.value3); //STATUS
        EditText editText4 = findViewById(R.id.value4); //REGION
        EditText editText5 = findViewById(R.id.value5); //REMARK


        button.setOnClickListener(view -> {
            String Employee_Name = editText1.getText().toString();
            String ORDER_NO = editText2.getText().toString();
            String STATUS = editText3.getText().toString();
            String REGION = editText4.getText().toString();
            String REMARK = editText5.getText().toString();

            RequestQueue queue = Volley.newRequestQueue(Employee_Ng.this);
            String url = "http://192.168.56.49/FYP/FYP_websiteData/User_Website_workVersion/addOrder_employee.php?ORDER_NO=" + ORDER_NO + "&STATUS=" + STATUS + "&REMARK=" + REMARK + "&Employee_Name=" + Employee_Name + "&REGION=" + REGION;

            StringRequest myReq = new StringRequest(Request.Method.GET, url,
                    response -> {
                        if (response.equals("Success")) {
                            Toast.makeText(Employee_Ng.this, "FAIL", Toast.LENGTH_SHORT).show();


                        } else Toast.makeText(Employee_Ng.this, "Data added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Employee_Ng.this, HomePage_Ng.class);
                        startActivity(intent);
                        finish();
                    },
                    error -> {
                        Log.e("Error", error.getLocalizedMessage());
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("ORDER_NO", ORDER_NO);
                    params.put("STATUS", STATUS);
                    params.put("REMARK", REMARK);
                    params.put("Employee_Name", Employee_Name);
                    params.put("REGION", REGION);

                    return params;
                }
            };
            queue.add(myReq);
        });
    }
}
