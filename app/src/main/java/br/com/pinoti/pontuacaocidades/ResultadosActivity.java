package br.com.pinoti.pontuacaocidades;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.pinoti.pontuacaocidades.adapter.CidadesAdapter;
import br.com.pinoti.pontuacaocidades.model.Cidade;
import br.com.pinoti.pontuacaocidades.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadosActivity extends AppCompatActivity {

    private List<Cidade> cidadesEncontradas;
    private ListView listResultados;
    private ProgressBar progressRequisicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        progressRequisicao = findViewById(R.id.progressBar);

        progressRequisicao.setVisibility(ProgressBar.VISIBLE);

        listResultados = findViewById(R.id.listResultados);

        final String buscaCidade = getIntent().getStringExtra("cidade");
        final String buscaEstado = getIntent().getStringExtra("estado");

        buscaCidades(buscaCidade, buscaEstado);

        listResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(ResultadosActivity.this, , Toast.LENGTH_SHORT).show();

                buscaPontos(cidadesEncontradas.get(i));

            }
        });
    }

    private void buscaPontos(final Cidade cidade) {
        Call<Object> call = new RetrofitInicializador().getCidadesService().buscaPontos(cidade);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                int pontos = (int) response.body();

                AlertDialog.Builder builder = new AlertDialog.Builder(ResultadosActivity.this);
                builder.setMessage("A pontuação da Cidade " + cidade.getNome() + " é: " + pontos)
                        .setPositiveButton("Ok", null);
                builder.create().show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(ResultadosActivity.this, "Falha ao obter Pontuação. Verifique a conexão e tente novamente!", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void atualizaLista(){
        CidadesAdapter adapter = new CidadesAdapter(this, cidadesEncontradas);
        listResultados.setAdapter(adapter);
        listResultados.setDivider(null);
    }

    private void buscaCidades(final String buscaCidade, final String buscaEstado) {
        Call<List<Cidade>> call = new RetrofitInicializador().getCidadesService().buscaTodas();
        call.enqueue(new Callback<List<Cidade>>() {
            @Override
            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                List<Cidade> cidades = response.body();

                cidadesEncontradas = new ArrayList<>();

                progressRequisicao.setVisibility(View.INVISIBLE);

                for(Cidade cidade : cidades){
                    if(cidade.getNome().toLowerCase().contains(buscaCidade.toLowerCase())
                            && cidade.getEstado().toLowerCase().contains(buscaEstado.toLowerCase())){
                        cidadesEncontradas.add(cidade);
                    }

                }

                if(cidadesEncontradas.size() > 0){
                    atualizaLista();
                }else{
                    Toast.makeText(ResultadosActivity.this, "Nenhuma cidade encontrada. Tente novamente!", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }

            @Override
            public void onFailure(Call<List<Cidade>> call, Throwable t) {
                progressRequisicao.setVisibility(View.INVISIBLE);
                Toast.makeText(ResultadosActivity.this, "Falha ao obter Cidades. Verifique a conexão e tente novamente!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
