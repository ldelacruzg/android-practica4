package app.smty.practica4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;


import app.smty.practica4.Adapters.EvaluadorAdapter;
import app.smty.practica4.Models.Evaluador;
import app.smty.practica4.Models.ResponseEvaluador;
import app.smty.practica4.Services.EvaluadoresAdminService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        // llamar función para la petición
        requestEvaluador();
    }

    private void rellenarLista(List<Evaluador> evaluadorList) {
        // Adaptador es la forma visual como se mostraran los datos
        layoutManager = new LinearLayoutManager(this);
        adapter = new EvaluadorAdapter(evaluadorList, R.layout.list_item_evaluador, new EvaluadorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Evaluador evaluador, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("e", evaluador.getIdevaluador());

                Intent intent = new Intent(MainActivity.this, FuncionariosActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        // establecemos el adaptador con nuestro recyclerView
        recyclerViewEvaluador.setItemAnimator(new DefaultItemAnimator());
        recyclerViewEvaluador.setLayoutManager(layoutManager);
        recyclerViewEvaluador.setAdapter(adapter);
    }

    private void requestEvaluador() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.uealecpeterson.net/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EvaluadoresAdminService service = retrofit.create(EvaluadoresAdminService.class);
        Call<ResponseEvaluador> evaluadores = service.getEvaluadores();
        evaluadores.enqueue(new Callback<ResponseEvaluador>() {
            @Override
            public void onResponse(Call<ResponseEvaluador> call, Response<ResponseEvaluador> response) {
                ResponseEvaluador body = response.body();
                rellenarLista(body.getListaaevaluador());
            }

            @Override
            public void onFailure(Call<ResponseEvaluador> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
