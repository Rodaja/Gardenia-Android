package com.rodaja.gardenia.model.firebase;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Storage {

    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private static boolean response = false;

    /**
     * Este metodo sirve para subir un archivo a Firebase Storage
     * @param file El archivo a subir
     * @param type El tipo de fichero
     *             0. Imagen
     *             1. Video
     *             2. Sonido
     *             3. Documento
     * @return True si el archivo ha sido cargado correctamente, false en caso contrario
     */
    public static boolean uploadFile(File file, int type){
        Uri fileUri = Uri.fromFile(file);
        String fileType = getFileType(type);

        StorageReference riversRef = storageReference.child(fileType + Authentication.getUserID() + "_" + file.getName());

        riversRef.putFile(fileUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        response = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                       response = false;
                    }
                });

        return response;
    }

    /**
     * Este metodo sirve para determinar la URL de un archivo en funcion del tipo de dato
     * @param type El tipo de fichero
     *             0. Imagen
     *             1. Video
     *             2. Sonido
     *             3. Documento
     * @return Un String con la ruta donde se guardará el fichero
     */
    private static String getFileType(int type) {
        String fileType = "";
        switch (type){
            case 0:
                fileType = "images/";
                break;
            case 1:
                fileType = "videos/";
                break;
            case 2:
                fileType = "sounds/";
                break;
            case 3:
                fileType = "documents/";
                break;
            default:
                fileType = "stuff/";
                break;
        }

        return fileType;
    }

    //TODO: Acabar

    /**
     * Este metodo sirve para subir un archivo a Firebase Storage
     * @param file El archivo a subir
     * @param type El tipo de fichero
     *             0. Imagen
     *             1. Video
     *             2. Sonido
     *             3. Documento
     * @return True si el archivo ha sido cargado correctamente, false en caso contrario
     */
    public static boolean downloadFile(File file, int type){
        Uri fileUri = Uri.fromFile(file);
        String fileType = getFileType(type);

        StorageReference riversRef = storageReference.child(fileType + Authentication.getUserID() + "_" + file.getName());

        riversRef.putFile(fileUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        response = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        response = false;
                    }
                });

        return response;
    }
}
