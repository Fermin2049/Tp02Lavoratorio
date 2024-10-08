package com.fermin2049.tp02laboratorio.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fermin2049.tp02laboratorio.model.Usuario;
import com.fermin2049.tp02laboratorio.request.ApiClient;
import com.fermin2049.tp02laboratorio.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private MutableLiveData<String> mensajeMutableLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        usuarioMutableLiveData = new MutableLiveData<>();
        mensajeMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<Usuario> getUsuario() {
        return usuarioMutableLiveData;
    }

    public LiveData<String> getMensaje() {
        return mensajeMutableLiveData;
    }

    // Método para manejar el login
    public void ingresar(String correo, String password) {
        Usuario usuario = ApiClient.login(context, correo, password);
        if (usuario != null) {
            usuarioMutableLiveData.setValue(usuario);
        } else {
            mensajeMutableLiveData.setValue("Correo o contraseña incorrectos");
        }
    }

    public void registrar() {
        // Manejar el registro del usuario e iniciar la actividad de Registro
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
