package app.smty.practica4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.smty.practica4.Adapters.EvaluadorAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewEvaluador;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializar components UI
        recyclerViewEvaluador = findViewById(R.id.recyclerViewEvaluador);

        // datos a mostrar
        List<String> names = new ArrayList<>();
        names.add("Daniela");
        names.add("Ana");
        names.add("Betania");

        // Adaptador es la forma visual como se mostraran los datos
        layoutManager = new LinearLayoutManager(this);
        adapter = new EvaluadorAdapter(names, R.layout.list_item_evaluador, new EvaluadorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                Toast.makeText(MainActivity.this, "Click " + name, Toast.LENGTH_LONG).show();
            }
        });

        // establecemos el adaptador con nuestro listView
        recyclerViewEvaluador.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEvaluador.setLayoutManager(layoutManager);
        recyclerViewEvaluador.setAdapter(adapter);
    }
}