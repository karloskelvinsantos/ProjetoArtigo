package br.com.karloskelvin.crudsqlite.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.karloskelvin.crudsqlite.R;


public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void adicionarContato(View view){
        intent = new Intent(this, AdicionarContatoActivity.class);
        startActivity(intent);
    }

    public void cargaDados(View view){
        intent = new Intent(this, InserirCargaDadosActivity.class);
        startActivity(intent);
    }

    public void listarContatos(View view){
        intent = new Intent(this, ListaContatosActivity.class);
        startActivity(intent);
    }
}
