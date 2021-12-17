package app.smty.practica4.Services;

import app.smty.practica4.Models.ResponseEvaluador;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EvaluadoresAdminService {
    @GET("listadoevaluadores.php")
    Call<ResponseEvaluador> getEvaluadores();
}
