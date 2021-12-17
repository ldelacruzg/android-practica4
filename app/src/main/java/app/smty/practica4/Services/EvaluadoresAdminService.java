package app.smty.practica4.Services;

import app.smty.practica4.Models.ResponseEvaluador;
import app.smty.practica4.Models.ResponseFuncionario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EvaluadoresAdminService {
    @GET("listadoevaluadores.php")
    Call<ResponseEvaluador> getEvaluadores();

    @GET("listadoaevaluar.php")
    Call<ResponseFuncionario> getFuncionarios(@Query("e") String e);
}
