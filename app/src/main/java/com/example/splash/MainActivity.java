package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.splash.adapters.ClientesAdaptador;
import com.example.splash.helpers.QueueUtils;
import com.example.splash.models.Clientes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView clientesList;
    ClientesAdaptador clientesAdaptador;
    QueueUtils.QueueObject queue = null;
    ArrayList<Clientes> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = QueueUtils.getInstance(this.getApplicationContext());
        items = new ArrayList<>();
        Clientes.injectContactsFromCloud(queue, items, this);

        clientesList = findViewById(R.id.clientesList);
        clientesAdaptador = new ClientesAdaptador(this, items);
        clientesList.setAdapter(clientesAdaptador);
    }

    public void refreshList(){
        if ( clientesAdaptador!= null ) {
            clientesAdaptador.notifyDataSetChanged();
        }
    }

}