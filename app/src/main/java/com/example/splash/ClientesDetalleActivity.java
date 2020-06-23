package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.splash.helpers.QueueUtils;
import com.example.splash.models.Clientes;

public class ClientesDetalleActivity extends AppCompatActivity {
    QueueUtils.QueueObject queue = null;
    int clientesId;
    Clientes clientesObject = new Clientes(0, "", "", "");
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_detalle);
        Intent o =getIntent();
        clientesId = o.getIntExtra("clientesId", -1);
        if ( clientesId <= -1 ){
            Toast.makeText(this, "No se selecciono un contacto", Toast.LENGTH_SHORT).show();
        }
        /// consumimos informacion detallada de la nube
        clientesObject.id = clientesId;
        queue = QueueUtils.getInstance(this.getApplicationContext());
        imageLoader = queue.getImageLoader();
        Clientes.injectContactFromCloud(queue, clientesObject, this);
    }
    public void refresh(){
        TextView txtNombre = findViewById(R.id.txtNombre);
        txtNombre.setText(clientesObject.nickname);
        NetworkImageView imgFoto = findViewById(R.id.imgFoto);
        imgFoto.setImageUrl(clientesObject.urlImage, imageLoader);
    }
}
