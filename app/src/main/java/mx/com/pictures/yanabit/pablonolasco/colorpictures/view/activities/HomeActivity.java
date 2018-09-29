package mx.com.pictures.yanabit.pablonolasco.colorpictures.view.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import mx.com.pictures.yanabit.pablonolasco.colorpictures.R;
import mx.com.pictures.yanabit.pablonolasco.colorpictures.view.util.AlerDialogUtils;
import mx.com.pictures.yanabit.pablonolasco.colorpictures.view.util.Constans;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cv_camera;
    private CardView cv_camera_galery;
    private CardView cv_video;
    private CardView cv_video_galely;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cv_camera=(CardView)findViewById(R.id.cv_camera);
        cv_camera_galery=(CardView)findViewById(R.id.cv_camera_libary);
        cv_video=(CardView)findViewById(R.id.cv_video_camera);
        cv_video_galely=(CardView)findViewById(R.id.cv_video_library);
        cv_camera.setOnClickListener(this);
        cv_camera_galery.setOnClickListener(this);
        cv_video.setOnClickListener(this);
        cv_video_galely.setOnClickListener(this);

    }

    private void takePhoto(){
        //inten para capturar imagen
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //inicia actividad y retornar un valor
        startActivityForResult(intent, Constans.PETICION_FOTO);

    }
    @Override
    public void onClick(View view) {
        int id=view.getId();
        if (id == cv_camera.getId()){
            if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1) {// Marshmallow+
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    AlerDialogUtils.showSimpleAlertDialog(getApplicationContext(),"Mensaje","Activa Permiso de Camara");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        AlerDialogUtils.showSimpleAlertDialog(getApplicationContext(),"Mensaje","Activa Permiso de Camara");

                    } else {
                        // No se necesita dar una explicación al usuario, sólo pedimos el permiso.
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, 1 );
                        // MY_PERMISSIONS_REQUEST_CAMARA es una constante definida en la app. El método callback obtiene el resultado de la petición.
                    }
                }else{ //have permissions
                    takePhoto();
                }
            }else{ // Pre-Marshmallow
                takePhoto ();
            }


        }else if(id == cv_camera_galery.getId()){
            Toast.makeText(getApplicationContext(),"camera galery",Toast.LENGTH_SHORT).show();
        }else if(id == cv_video.getId()){
            Toast.makeText(getApplicationContext(),"video",Toast.LENGTH_SHORT).show();
        }else if(id == cv_video_galely.getId()){
            Toast.makeText(getApplicationContext(),"video galery",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean almacenamientoExternoDisponible(){
        //verificar el estado de almacenamiento
        String estado= Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1 : {
                // Si la petición es cancelada, el array resultante estará vacío.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso ha sido concedido.
                    takePhoto();
                } else {
                    // Permiso denegado, deshabilita la funcionalidad que depende de este permiso.
                    AlerDialogUtils.showSimpleAlertDialog(getApplicationContext(),"Mensaje","");
                }
                return;
            }
            // otros bloques de 'case' para controlar otros permisos de la aplicación
        }
    }
}
