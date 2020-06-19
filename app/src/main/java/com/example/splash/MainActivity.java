package com.example.splash;

//Integrantes

//Condor Lanazca Cristhian
//Estrella Vidal Nelson
//Rodriguez Mercado Yerry

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.splash.adapters.ClientesAdaptador;
import com.example.splash.helpers.QueueUtils;
import com.example.splash.models.Clientes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView clientesList;
    ClientesAdaptador clientesAdaptador;
    QueueUtils.QueueObject queue = null;
    ImageLoader queueImage = null;
    ArrayList<Clientes> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = QueueUtils.getInstance(this.getApplicationContext());
        queueImage = queue.getImageLoader();
        items = new ArrayList<>();
        Clientes.injectContactsFromCloud(queue, items, this);

        clientesList = findViewById(R.id.clientesList);
        clientesAdaptador = new ClientesAdaptador(this, items, queueImage);
        clientesList.setAdapter(clientesAdaptador);

        clientesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // En esta area puedo solicitar mas datos a la nube
                Clientes registro = items.get(position);
                showDetails(registro);
                //Toast.makeText(MainActivity.this, "Persona" + registro.phone,
                  //      Toast.LENGTH_SHORT).show();
            }
        });
    }

        public void showDetails(Clientes item){
            Intent o = new Intent(this, ClientesDetalleActivity.class);
            o.putExtra("clientesId", item.id);
            startActivity(o);
        }

    public void refreshList(){
        if ( clientesAdaptador!= null ) {
            clientesAdaptador.notifyDataSetChanged();
        }
    }

}