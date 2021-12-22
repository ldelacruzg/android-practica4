package app.smty.practica4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.security.cert.CertificateException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import app.smty.practica4.Adapters.EvaluadorAdapter;
import app.smty.practica4.Adapters.FuncionarioAdapter;
import app.smty.practica4.Models.Evaluador;
import app.smty.practica4.Models.Funcionario;
import app.smty.practica4.Models.ResponseEvaluador;
import app.smty.practica4.Models.ResponseFuncionario;
import app.smty.practica4.Services.EvaluadoresAdminService;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FuncionariosActivity extends AppCompatActivity {
    RecyclerView recyclerViewFuncionarios;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionarios);

        // inicializar components UI
        recyclerViewFuncionarios = findViewById(R.id.recyclerViewFuncionarios);

        // Bundle para enviar los datos
        Bundle bundle = getIntent().getExtras();
        String e = bundle.getString("e");

        // llamar función para la petición
        requestFuncionarios(e);
    }

    private void rellenarLista(List<Funcionario> funcionariosList) {
        // Adaptador es la forma visual como se mostraran los datos
        layoutManager = new LinearLayoutManager(this);
        adapter = new FuncionarioAdapter(funcionariosList, R.layout.list_item_funcionario);

        // establecemos el adaptador con nuestro recyclerView
        recyclerViewFuncionarios.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFuncionarios.setLayoutManager(layoutManager);
        recyclerViewFuncionarios.setAdapter(adapter);
    }

    private void requestFuncionarios(String e) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uealecpeterson.net/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EvaluadoresAdminService service = retrofit.create(EvaluadoresAdminService.class);
        Call<ResponseFuncionario> funcionarios = service.getFuncionarios(e);
        funcionarios.enqueue(new Callback<ResponseFuncionario>() {
            @Override
            public void onResponse(Call<ResponseFuncionario> call, Response<ResponseFuncionario> response) {
                ResponseFuncionario body = response.body();
                rellenarLista(body.getListaaevaluar());
            }

            @Override
            public void onFailure(Call<ResponseFuncionario> call, Throwable t) {
                Toast.makeText(FuncionariosActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}