package br.com.pinoti.pontuacaocidades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editCidade = findViewById(R.id.editCidade);
        final EditText editEstado = findViewById(R.id.editEstado);
        Button btBuscar = findViewById(R.id.btBuscar);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cidade = editCidade.getText().toString();
                String estado = editEstado.getText().toString();

                if(cidade.length() > 0 || estado.length() > 0){
                    Intent intent = new Intent(MainActivity.this, ResultadosActivity.class);
                    intent.putExtra("cidade", cidade);
                    intent.putExtra("estado", estado);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Preencha ao menos um dos campos!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
