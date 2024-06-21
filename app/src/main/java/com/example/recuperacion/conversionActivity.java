package com.example.recuperacion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class conversionActivity extends AppCompatActivity {
    private EditText txtCantidad;
    private RadioGroup radioGroup;
    private RadioButton rdbCel, rdbFa;
    private TextView lblResultado;
    private TextView lblNombre;
    private Button btnCalcular, btnLimpiar, btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversion);


        txtCantidad = findViewById(R.id.txtCantidad);
        rdbCel = findViewById(R.id.rdbCel);
        rdbFa = findViewById(R.id.rdbFa);
        lblNombre = findViewById(R.id.lblNombre);
        lblResultado = findViewById(R.id.lblResultado);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnCerrar = findViewById(R.id.btnCerrar);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String cantidadStr = txtCantidad.getText().toString().trim();
                    if (cantidadStr.isEmpty()) {
                        throw new IllegalArgumentException("El campo de cantidad no puede estar vacío.");
                    }

                    double cantidad = Double.parseDouble(cantidadStr);
                    double resultado;
                    if (rdbCel.isChecked()) {
                        resultado = (cantidad * 9/5) + 32;
                        lblResultado.setText("Resultado: " + String.format("%.2f", resultado) + " °F");
                    } else if (rdbFa.isChecked()) {

                        resultado = (cantidad - 32) * 5/9;
                        lblResultado.setText("Resultado: " + String.format("%.2f", resultado) + " °C");
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(conversionActivity.this, "Inserta un valor numérico para la cantidad, por favor.", Toast.LENGTH_SHORT).show();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(conversionActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCantidad.setText("");
                lblResultado.setText("Resultado:");
                radioGroup.clearCheck();
                rdbCel.setChecked(true);
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
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


        Bundle datos = getIntent().getExtras();
        String nombre = datos.getString("USUARIO");
        lblNombre.setText("Bienvenido " + nombre);
    }
}
