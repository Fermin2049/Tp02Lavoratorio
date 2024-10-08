package com.fermin2049.tp02laboratorio.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.fermin2049.tp02laboratorio.R;
import com.fermin2049.tp02laboratorio.databinding.ActivityMainBinding;
import com.fermin2049.tp02laboratorio.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Observamos el cambio en el usuario
        mainActivityViewModel.getUsuario().observe(this, usuario -> {
            if (usuario != null) {
                navegarARegistro(); // Navegar a RegistroActivity si el login es exitoso
            }
        });

        // Observamos los mensajes de error
        mainActivityViewModel.getMensaje().observe(this, mensaje -> {
            mostrarMensajeError(mensaje); // Mostrar el mensaje de error si el login falla
        });

        // Acción del botón Ingresar
        binding.btnIngresar.setOnClickListener(v -> {
            String correo = binding.etMail.getText().toString();
            String password = binding.etPassword.getText().toString();
            mainActivityViewModel.ingresar(correo, password);  // Ingresar con las credenciales
        });

        // Acción del botón Registrar
        binding.btnRegistrar.setOnClickListener(v -> {
            mainActivityViewModel.registrar();  // Navegar al registro
        });
    }

    private void navegarARegistro() {
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    private void mostrarMensajeError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}