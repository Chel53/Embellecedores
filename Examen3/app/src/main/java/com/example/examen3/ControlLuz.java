package com.example.examen3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ControlLuz extends AppCompatActivity {
    Switch switch2;
    Integer  statusLuz;
    ImageButton btniLuz;
    Button btnBackToHomeL;
    ImageButton btnReloadLuz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_luz);
        switch2 = findViewById(R.id.switch2);
        btniLuz = findViewById(R.id.btniLuz);
        statusLuz = 1;
        btniLuz.setBackgroundDrawable(null);

        btnBackToHomeL = (Button) findViewById( R.id.btnBackToHomeL );
        //btnReloadTLuz = (ImageButton) findViewById( R.id.btnReloadLuz);

        validarstatusLuz();


        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar("http://192.168.114.225/serviceexamen/regprodfact.php");

                if (statusLuz == 1) {
                    statusLuz = 0;
                    validarstatusLuz();
                } else {
                    statusLuz = 1;
                    validarstatusLuz();
                }

            }
        });

        btnBackToHomeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // -->> Enviar a pantalla de MENU Principal
                Intent intent = new Intent(ControlLuz.this, MenuPrincipalActivity.class);
                startActivity( intent );
                finish();
            }
        }); //--fin: btnBackToHome.clickListener()

        btnReloadLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : Actualizar temperatura [Consultar Temperatura en BD]
                //consultarTemperaturaBD( urlConsultaTempCorp );
            }
        }); //--fin: btnReloadTemperature.clickListener()
    }



    public void validarstatusLuz(){
        if (statusLuz==1){//Cuando el switch es checado
            switch2.setChecked(true);
            switch2.setText("Apagar la luz");
            btniLuz.setImageResource(R.drawable.encendido);
            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();

        }else{
            switch2.setChecked(false);
            switch2.setText("Encender la luz");
            btniLuz.setImageResource(R.drawable.apagado);
            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT).show();


        }
    }
    private void actualizar(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "operción realiizada", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}