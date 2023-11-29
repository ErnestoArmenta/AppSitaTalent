package com.app.sitaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity{
    Button btnCredencial, btnSeguro, btnPassword, btnCerrar;
    String rfc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnCredencial = findViewById(R.id.btnCredencial);
        btnSeguro = findViewById(R.id.btnSeguro);
        btnPassword = findViewById(R.id.btnPassword);
        btnCerrar = findViewById(R.id.btnCerrar);
        rfc = getRFC();
        btnCredencial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CredencialActivity.class);
                i.putExtra("rfc", rfc);
                startActivity(i);
            }
        });
    }

    private String getRFC(){
        Bundle extras = getIntent().getExtras();
        String rfc = extras.getString("rfc");
        return rfc;
    }
}