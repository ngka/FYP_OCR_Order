package com.example.fyp_ocr_order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class User_SendProblem_Ng extends AppCompatActivity {
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_ng);
        setTitle("User Input data");

        button = findViewById(R.id.submit);
        EditText editText1 = findViewById(R.id.value1);
        EditText editText2 = findViewById(R.id.value2);
        EditText editText3 = findViewById(R.id.value3);
        EditText editText4 = findViewById(R.id.value4); //added_txt
        EditText editText5 = findViewById(R.id.value5);
        EditText editText6 = findViewById(R.id.value6);

        button.setOnClickListener(view -> {
            String Title = editText1.getText().toString();
            String description_txt = editText2.getText().toString();
            String day = editText3.getText().toString();
            String month = editText4.getText().toString();
            String added_txt = editText5.getText().toString();
            String year = editText6.getText().toString();

            // Create JSON object
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("Title", Title);
                jsonObject.put("description_txt", description_txt);
                jsonObject.put("day", day);
                jsonObject.put("month", month);
                jsonObject.put("year", year);
                jsonObject.put("added_txt", added_txt);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray;
            File file = new File(getFilesDir(), "data1.json");
            if(file.exists()) {
                // If file exists, read the existing JSON array
                try {
                    FileInputStream fis = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                    jsonArray = new JSONArray(sb.toString());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    jsonArray = new JSONArray();
                }
            } else {
                // If file does not exist, create a new JSON array
                jsonArray = new JSONArray();
            }

            // Add the new JSON object to the JSON array
            jsonArray.put(jsonObject);

            // Convert JSON array to string
            String jsonString = jsonArray.toString();

            // Save JSON string to file
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jsonString);
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = "http://192.168.56.49/FYP/FYP_websiteData/User_Website_workVersion/addOrder_employee.php?Title=" + Title + "&description=" + description_txt + "&day=" + day + "&month=" + month + "&year=" + year+ "&added_txt=" + added_txt;

            StringRequest myReq = new StringRequest(Request.Method.GET, url,
                    response -> {
                        if (response.equals("Success")) {
                            Toast.makeText(User_SendProblem_Ng.this, "FAIL", Toast.LENGTH_SHORT).show();

                        } else Toast.makeText(User_SendProblem_Ng.this, "Data added", Toast.LENGTH_SHORT).show();
                    },
                    error -> {
                        Log.e("Error", error.getLocalizedMessage());
                    });

            Volley.newRequestQueue(User_SendProblem_Ng.this).add(myReq);

            Intent intent = new Intent(User_SendProblem_Ng.this, PublicuserPage_Ng.class);
            intent.putExtra("Title", Title);
            intent.putExtra("description_txt", description_txt);
            intent.putExtra("day", day);
            intent.putExtra("month", month);
            intent.putExtra("year", year);
            intent.putExtra("added_txt", added_txt);
            startActivity(intent);
        });
    }
}