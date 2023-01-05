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

public class ControlPuerta extends AppCompatActivity {
    Switch switch1;
    Integer statusPuer;
    ImageButton btniPuer;
    Button btnBackToHomeP;
    ImageButton btnReloadPuerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_puerta);
        switch1 = findViewById(R.id.switch1);
        btniPuer = findViewById(R.id.btniPuer);
        statusPuer = 1;
        btniPuer.setBackgroundDrawable(null);
        btnBackToHomeP = (Button) findViewById( R.id.btnBackToHomeP );
        //btnReloadTPuerta = (ImageButton) findViewById( R.id.btnReloadPuerta);


        validarstatusPuer();

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar("http://192.168.114.225/serviceexamen/regprodfact.php");

                if (statusPuer == 1) {
                    statusPuer = 0;
                    validarstatusPuer();
                } else {
                    statusPuer = 1;
                    validarstatusPuer();
                }
            }
        });

        btnBackToHomeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // -->> Enviar a pantalla de MENU Principal
                Intent intent = new Intent(ControlPuerta.this, MenuPrincipalActivity.class);
                startActivity( intent );
                finish();
            }
        }); //--fin: btnBackToHome.clickListener()

        /*
        btnReloadPuerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : Actualizar temperatura [Consultar Temperatura en BD]
                //consultarTemperaturaBD( urlConsultaTempCorp );
            }
        }); //--fin: btnReloadPuerta.clickListener()
        */

    }

    public void validarstatusPuer(){
        if (statusPuer==1){//Cuando el switch es checado
            switch1.setChecked(true);
            switch1.setText("Cerrar la puerta");
            btniPuer.setImageResource(R.drawable.abierta);
            Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();

        }else{
            switch1.setChecked(false);
            switch1.setText("Abrir la puerta");
            btniPuer.setImageResource(R.drawable.cerrada);
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