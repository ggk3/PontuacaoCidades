package br.com.pinoti.pontuacaocidades.retrofit;

import br.com.pinoti.pontuacaocidades.service.CidadesService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador(){

        retrofit = new Retrofit.Builder()
                .baseUrl("http://wsteste.devedp.com.br/Master/CidadeServico.svc/rest/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }

    public CidadesService getCidadesService(){
        return retrofit.create(CidadesService.class);
    }
}
