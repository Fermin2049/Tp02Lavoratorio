package com.fermin2049.tp02laboratorio.ui.registro;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fermin2049.tp02laboratorio.model.Usuario;
import com.fermin2049.tp02laboratorio.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private Context context;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        usuarioMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<Usuario> getUsuario() {
        return usuarioMutableLiveData;
    }

    public void cargarDatosUsuario() {
        Usuario usuario = ApiClient.leer(context);
        if (usuario != null) {
            usuarioMutableLiveData.setValue(usuario);
        }
    }

    public void guardarUsuario(Usuario usuario) {
        boolean guardado = ApiClient.guardar(context, usuario);
        if (!guardado) {
            Toast.makeText(context, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
