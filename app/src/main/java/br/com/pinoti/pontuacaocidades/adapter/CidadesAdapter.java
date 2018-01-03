package br.com.pinoti.pontuacaocidades.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.pinoti.pontuacaocidades.R;
import br.com.pinoti.pontuacaocidades.model.Cidade;

public class CidadesAdapter extends BaseAdapter{

    private final Context context;
    private final List<Cidade> cidades;

    public CidadesAdapter(Context c, List<Cidade> cidades){

        context = c;
        this.cidades = cidades;
    }

    @Override
    public int getCount() {
        return cidades.size();
    }

    @Override
    public Object getItem(int i) {
        return cidades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Cidade cidade = cidades.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);

        View viewNova = view;
        if(view == null){
            viewNova = inflater.inflate(R.layout.list_item_cidade,null);
        }

        TextView nomeCidade = viewNova.findViewById(R.id.tvNomeCidade);
        TextView nomeEstado = viewNova.findViewById(R.id.tvNomeEstado);

        nomeCidade.setText("Cidade: " + cidade.getNome());
        nomeEstado.setText("Estado: " + cidade.getEstado());

        return viewNova;


    }
}
