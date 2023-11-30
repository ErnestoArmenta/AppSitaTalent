package com.app.sitaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CredencialActivity extends AppCompatActivity {
    String rfc;
    TextView nombre, area, sistema, comp;

    RequestQueue requestQueue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credencial);
        Bundle recibeDatos = getIntent().getExtras();
        rfc = recibeDatos.getString("rfc");

        nombre = findViewById(R.id.nombre);
        area = findViewById(R.id.area);
        sistema = findViewById(R.id.sistema);
        comp = findViewById(R.id.comp);

        buscarEmp();
    }

    private void buscarEmp(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://192.168.100.9/WebProject/api/searchemployee.php?rfc="+rfc+"", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nombre.setText(jsonObject.getString("nombre_comp"));
                        area.setText(jsonObject.getString("Area"));
                        sistema.setText(jsonObject.getString("Sistema"));
                        comp.setText(jsonObject.getString("Compania"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}