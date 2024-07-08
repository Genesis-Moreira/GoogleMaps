package com.example.interfazdeusuariouideandroid;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // Recuperar datos pasados desde MainActivity
        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String empresa = getIntent().getStringExtra("empresa");
        String telefono = getIntent().getStringExtra("telefono");
        String correo = getIntent().getStringExtra("correo");

        // Mostrar los datos en TextViews u otros componentes
        TextView txtNombre = findViewById(R.id.txtNombre);
        TextView txtApellido = findViewById(R.id.txtApellido);
        TextView txtEmpresa = findViewById(R.id.txtEmpresa);
        TextView txtTelefono = findViewById(R.id.txtTelefono);
        TextView txtCorreo = findViewById(R.id.txtCorreo);

        // Verificar si los datos recibidos son nulos (para evitar NPE)
        if (nombre != null) {
            txtNombre.setText("Nombre: " + nombre);
        }
        if (apellido != null) {
            txtApellido.setText("Apellido: " + apellido);
        }
        if (empresa != null) {
            txtEmpresa.setText("Empresa: " + empresa);
        }
        if (telefono != null) {
            txtTelefono.setText("Teléfono: " + telefono);
        }
        if (correo != null) {
            txtCorreo.setText("Correo: " + correo);
        }
    }
}