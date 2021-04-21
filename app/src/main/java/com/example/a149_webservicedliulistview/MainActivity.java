package com.example.a149_webservicedliulistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSinhVien;
    ArrayList<SinhVien> sinhVienArrayList;
    SinhVienAdapter adapter;

    String urlGetdata = "http://172.19.200.117/androidwebservice/getdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSinhVien = findViewById(R.id.listViewSinhVien);
        sinhVienArrayList = new ArrayList<>();

        adapter = new SinhVienAdapter(this,R.layout.dong_sinh_vien,sinhVienArrayList);
        lvSinhVien.setAdapter(adapter);

        Getdata(urlGetdata);
    }

    private void Getdata(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <  response.length(); i++){
                    JSONObject object = null;
                    try {
                        object = response.getJSONObject(i);
                        sinhVienArrayList.add(new SinhVien(
                                object.getInt("ID"),
                                object.getString("HoTen"),
                                object.getInt("NamSinh"),
                                object.getString("DiaChi")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Loi khong the hien thi", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    //hien thi
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addstudent,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //su kien
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAddSinhVIen){
            startActivity(new Intent(MainActivity.this,Activity_AddSV.class));
        }
        return super.onOptionsItemSelected(item);
    }
}