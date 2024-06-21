package com.example.recuperacion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class imcActivity extends AppCompatActivity {
    private TextView lblResultado;
    private TextView lblnombre;
    private EditText txtAlturaM;
    private EditText txtAlturacm;
    private EditText txtPeso;
    private Button btnCalcular;
    private Button btnLimpiar;
    private Button btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imc);

        init();
        eventosClic();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void init() {
        lblnombre = (TextView) findViewById(R.id.lblnombre);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);
        btnCalcular =(Button) findViewById(R.id.btnCalcular);
        btnLimpiar =(Button) findViewById(R.id.btnLimpiar);
        txtAlturaM =(EditText) findViewById(R.id.txtAlturaM);
        txtAlturacm =(EditText) findViewById(R.id.txtAlturacm);
        txtPeso =(EditText) findViewById(R.id.txtPeso);
        lblResultado =(TextView) findViewById(R.id.lblResultado);

        Bundle datos = getIntent().getExtras();
        String nombre = datos.getString("USUARIO");
        lblnombre.setText("Bienvenido " + nombre);
    }

    public void eventosClic() {
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String alturaMStr = txtAlturaM.getText().toString();
                    String alturaCmStr = txtAlturacm.getText().toString();
                    String pesoStr = txtPeso.getText().toString();

                    if (alturaMStr.isEmpty() || alturaCmStr.isEmpty() || pesoStr.isEmpty()) {
                        throw new IllegalArgumentException("Todos los campos deben estar llenos.");
                    }

                    double alturaM = Double.parseDouble(alturaMStr);
                    double alturaCm = Double.parseDouble(alturaCmStr);
                    double altura = alturaM + (alturaCm / 100);
                    double peso = Double.parseDouble(pesoStr);

                    if (altura <= 0 || peso <= 0) {
                        throw new IllegalArgumentException("Los valores no pueden ser iguales o menores a 0.");
                    }

                    double imc = peso / Math.pow(altura, 2);
                    DecimalFormat df = new DecimalFormat("#.##");
                    String imcStr = df.format(imc);

                    lblResultado.setText("Tu IMC es: " + imcStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(imcActivity.this, "Inserta un valor numérico para altura y peso, por favor.",
                            Toast.LENGTH_SHORT).show();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(imcActivity.this, "Error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblResultado.setText("Tu IMC es: ");
                txtAlturaM.setText("");
                txtAlturacm.setText("");
                txtPeso.setText("");
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
