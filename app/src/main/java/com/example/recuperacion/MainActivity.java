package com.example.recuperacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText txtUser;
    private Button btnIMC, btnConver, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        iniciarComponentes();

        btnIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUser.getText().toString().trim();
                if (!username.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, imcActivity.class);
                    intent.putExtra("USUARIO", txtUser.getText().toString().trim());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Algo salió mal, intenta de nuevo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUser.getText().toString().trim();
                if (!username.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, conversionActivity.class);
                    intent.putExtra("USUARIO",txtUser.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Algo salió mal, intenta de nuevo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void iniciarComponentes(){
        txtUser = (EditText) findViewById(R.id.txtuser);
        btnIMC = (Button) findViewById(R.id.btnIMC);
        btnConver =(Button) findViewById(R.id.btnConversion);
        btnSalir = (Button) findViewById(R.id.btnCerrar);
    }
}
