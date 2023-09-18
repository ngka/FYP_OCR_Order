package com.example.fyp_ocr_order;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EmployeeUpdate_Ng extends AppCompatActivity {
    Button dateButton,statusButton ;
    String date, STATUS;  // Declare STATUS here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ng);
        setTitle("Update information");

        EditText edit_EmployeeName = findViewById(R.id.EmployeeName);
        EditText edit_OrderID = findViewById(R.id.OrderID);
        dateButton = findViewById(R.id.dateButton);  // Assumes you have a Button with this id in your layout
        statusButton = findViewById(R.id.status_button);
        ImageButton button = findViewById(R.id.submit);
        dateButton.setOnClickListener(view -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    EmployeeUpdate_Ng.this,
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        date = year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        dateButton.setText(date);
                        dateButton.setBackgroundColor(Color.TRANSPARENT);
                    },
                    year,
                    month,
                    day);
            datePickerDialog.show();
        });

        statusButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeUpdate_Ng.this);
            builder.setTitle("Choose a status")
                    .setItems(new String[]{"open", "closed"},
                            (dialog, which) -> {
                                switch (which) {
                                    case 0:
                                        STATUS = "open";
                                        break;
                                    case 1:
                                        STATUS = "closed";
                                        break;
                                }
                                statusButton.setText(STATUS);
                                statusButton.setBackgroundColor(Color.TRANSPARENT);
                            });
            builder.create().show();
        });

        button.setOnClickListener(view -> {
            String EmployeeName = edit_EmployeeName.getText().toString();
            String OrderID = edit_OrderID.getText().toString();

            RequestQueue queue = Volley.newRequestQueue(EmployeeUpdate_Ng.this);
            String url = "http://192.168.56.49/FYP/FYP_websiteData/User_Website_workVersion/update_employee.php?Employee_Name="+ EmployeeName + "&OrderID=" + OrderID + "&Date=" + date +"&STATUS=" + STATUS;

            StringRequest myReq = new StringRequest(Request.Method.GET, url,
                    response -> {
                        if (response.equals("Fail")){
                            Toast.makeText(EmployeeUpdate_Ng.this, "FAIL", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(EmployeeUpdate_Ng.this, "Data added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EmployeeUpdate_Ng.this, EmployeeUpdate_Ng.class);
                        startActivity(intent);
                        finish();
                    },
                    error -> Log.e("Error", error.getLocalizedMessage())) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Employee_Name", EmployeeName);
                    params.put("OrderID", OrderID);
                    params.put("Date", date);
                    params.put("STATUS", STATUS);

                    return params;
                }
            };
            queue.add(myReq);
        });
    }
}
