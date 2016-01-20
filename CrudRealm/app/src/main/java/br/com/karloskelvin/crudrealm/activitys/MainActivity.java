package br.com.karloskelvin.crudrealm.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.karloskelvin.crudrealm.R;

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
        intent = new Intent(this, AdicionarCargaDadosActivity.class);
        startActivity(intent);
    }

    public void listarContatos(View view){
        intent = new Intent(this, ListarContatosActivity.class);
        startActivity(intent);
    }
}
