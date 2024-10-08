package com.fermin2049.tp02laboratorio.request;

import android.content.Context;
import android.widget.Toast;

import com.fermin2049.tp02laboratorio.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
    private static File file;

    private static File conectar(Context context) {
        if (file == null) {
            file = new File(context.getFilesDir(), "usuario.obj");
        }
        return file;
    }

    // Guardar los datos del usuario en un archivo
    public static boolean guardar(Context context, Usuario usuario) {
        File file = conectar(context);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(usuario);
            oos.flush();
            bos.close();
            return true;
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Archivo no encontrado: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            Toast.makeText(context, "Error de entrada/salida: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }

    // Leer los datos del usuario desde un archivo
    public static Usuario leer(Context context) {
        Usuario usuario = null;
        File file = conectar(context);
        if (!file.exists()) {
            Toast.makeText(context, "El archivo no existe.", Toast.LENGTH_LONG).show();
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            usuario = (Usuario) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Archivo no encontrado: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "Error de entrada/salida: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "Error al obtener el usuario: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return usuario;
    }

    // Realizar el login verificando mail y password
    public static Usuario login(Context context, String mail, String password) {
        Usuario usuario = leer(context);
        if (usuario != null) {
            if (usuario.getMail().equals(mail) && usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }
}
