package com.example.interfazdeusuariouideandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Pattern email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Paso 1: Datos
        String[] datos = new String[]{"Fijo", "Movil"};

// Paso 2: Crear el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, datos);

// Paso 3: Asignar el adaptador
        AutoCompleteTextView textView = findViewById(R.id.actvLista);
        textView.setAdapter(adapter);

// Asegúrate de que el adaptador no esté vacío
        if (textView.getAdapter() == null || textView.getAdapter().isEmpty()) {
            Log.e("AutoCompleteTextView", "Adapter is null or empty");
        } else {
            // Establecer un texto por defecto para asegurar que hay texto disponible
            textView.setText("Fijo", false);
        }


    }
    public void clickbBotonGuardar(View view) {
        // Validación de campos
        boolean isValid = validarCampos();

        if (isValid) {
            // Crear Intent para pasar datos al nuevo Activity
            Intent intent = new Intent(this, Resultado.class);

            // Obtener y pasar datos ingresados
            TextInputEditText txtNombre = findViewById(R.id.txtNombre);
            String nombre = txtNombre.getText().toString().trim();
            intent.putExtra("nombre", nombre);

            TextInputEditText txtApellido = findViewById(R.id.txtApellido);
            String apellido = txtApellido.getText().toString().trim();
            intent.putExtra("apellido", apellido);

            TextInputEditText txtEmpresa = findViewById(R.id.txtEmpresa);
            String empresa = txtEmpresa.getText().toString().trim();
            intent.putExtra("empresa", empresa);

            TextInputEditText txtTelefono = findViewById(R.id.txtTelefono);
            String telefono = txtTelefono.getText().toString().trim();
            intent.putExtra("telefono", telefono);

            TextInputEditText txtCorreo = findViewById(R.id.txtCorreo);
            String correo = txtCorreo.getText().toString().trim();
            intent.putExtra("correo", correo);

            // Iniciar el nuevo Activity
            startActivity(intent);
        }
    }

    private boolean validarCampos() {
        boolean isValid = true;

        TextInputLayout idNombre = findViewById(R.id.idNombre);
        TextInputEditText txtNombre = findViewById(R.id.txtNombre);
        String nombre = txtNombre.getText().toString().trim();
        if (nombre.equals("") || nombre.length() > 150) {
            idNombre.setError("Ingrese un nombre válido");
            isValid = false;
        } else {
            idNombre.setError(null);
            idNombre.setHelperText("Ingresado Correctamente");
        }

        TextInputLayout idApellido = findViewById(R.id.idApellido);
        TextInputEditText txtApellido = findViewById(R.id.txtApellido);
        String apellido = txtApellido.getText().toString().trim();
        if (apellido.equals("") || apellido.length() > 150) {
            idApellido.setError("Ingrese un Apellido válido");
            isValid = false;
        } else {
            idApellido.setError(null);
            idApellido.setHelperText("Ingresado Correctamente");
        }

        TextInputLayout idEmpresa = findViewById(R.id.idEmpresa);
        TextInputEditText txtEmpresa = findViewById(R.id.txtEmpresa);
        String empresa = txtEmpresa.getText().toString().trim();
        if (empresa.equals("") || empresa.length() > 150) {
            idEmpresa.setError("Ingrese un nombre de Empresa válido");
            isValid = false;
        } else {
            idEmpresa.setError(null);
            idEmpresa.setHelperText("Ingresado Correctamente");
        }

        TextInputLayout idTelefono = findViewById(R.id.idTelefono);
        TextInputEditText txtTelefono = findViewById(R.id.txtTelefono);
        String telefono = txtTelefono.getText().toString().trim();
        if (telefono.equals("") || telefono.length() != 10) {
            idTelefono.setError("Ingrese un número de teléfono válido (10 dígitos)");
            isValid = false;
        } else {
            idTelefono.setError(null);
            idTelefono.setHelperText("Ingresado Correctamente");
        }

        TextInputLayout idCorreo = findViewById(R.id.idCorreo);
        TextInputEditText txtCorreo = findViewById(R.id.txtCorreo);
        String correo = txtCorreo.getText().toString().trim();
        if (correo.equals("") || correo.length() > 100) {
            idCorreo.setError("Ingrese un correo válido");
            isValid = false;
        } else if (!isValidEmail(correo)) {
            idCorreo.setError("Ingrese un correo electrónico válido");
            isValid = false;
        } else {
            idCorreo.setError(null);
            idCorreo.setHelperText("Correo electrónico ingresado correctamente");
        }

        return isValid;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(regex);
    }
}