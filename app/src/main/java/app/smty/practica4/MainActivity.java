package app.smty.practica4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import app.smty.practica4.Adapters.EvaluadorAdapter;
import app.smty.practica4.Models.Evaluador;
import app.smty.practica4.Models.ResponseEvaluador;
import app.smty.practica4.Services.EvaluadoresAdminService;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewEvaluador;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<Evaluador> evaluadorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestEvaluador();
    }

    private void rellenarLista(List<Evaluador> evaluadorList) {
        // inicializar components UI
        recyclerViewEvaluador = findViewById(R.id.recyclerViewEvaluador);

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
                .baseUrl("https://evaladmin.uteq.edu.ec/ws/")
                .client(getUnsafeOkHttpClient().build())
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

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}