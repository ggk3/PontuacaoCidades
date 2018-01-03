package br.com.pinoti.pontuacaocidades.service;

import java.util.List;
import br.com.pinoti.pontuacaocidades.model.Cidade;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CidadesService {

    @GET("BuscaTodasCidades")
    Call<List<Cidade>> buscaTodas();

    @POST("BuscaPontos")
    Call<Object> buscaPontos(@Body Cidade cidade);
}
