package com.example.a149_webservicedliulistview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Activity_AddSV extends AppCompatActivity {

    EditText edtHoTen, edtNamSinh, edtDiaChi;
    Button btnThemSV, btnHuySV;

    String urlinsert = "http://172.19.200.117/androidwebservice/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add_s_v);

        Anhxa();

        btnThemSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //5
                String hoTen = edtHoTen.getText().toString().trim();
                String namSinh = edtHoTen.getText().toString().trim();
                String diaChi = edtHoTen.getText().toString().trim();
                if (hoTen.isEmpty() || namSinh.isEmpty() || diaChi.isEmpty()){
                    Toast.makeText(Activity_AddSV.this, "Vui long nhap du thong tin SV", Toast.LENGTH_SHORT).show();
                }else {
                    ThemSinhVien(urlinsert);
                }
            }
        });
    }
    private void ThemSinhVien(String url){
        //GET lay du lieu
        //POST dua du lieu len
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //3
                //kiem tra co loi hay khong
                if (response.trim().equals("success")){
                    Toast.makeText(Activity_AddSV.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Activity_AddSV.this,MainActivity.class));
                }else {
                    Toast.makeText(Activity_AddSV.this, "Loi them SV", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_AddSV.this, "Xay ra loi! ", Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Loi!\n" + error.toString());
                    }
                }
        ){
            @Nullable
            @Override
            //2
            //String dau la key,thu 2 la value
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("hotenSV",edtHoTen.getText().toString().trim());
                params.put("namsinhSV",edtNamSinh.getText().toString().trim());
                params.put("diachiSV",edtDiaChi.getText().toString().trim());

                return params;
            }
        };
        //4
        requestQueue.add(stringRequest);
    }
    private void Anhxa(){
        edtHoTen = findViewById(R.id.edtTen);
        edtDiaChi = findViewById(R.id.edtDiachi);
        edtNamSinh = findViewById(R.id.edtNamSinh);
        btnHuySV = findViewById(R.id.btnHuySV);
        btnThemSV = findViewById(R.id.btnThemSV);
    }
}