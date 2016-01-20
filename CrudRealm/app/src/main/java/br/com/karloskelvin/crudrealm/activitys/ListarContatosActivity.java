package br.com.karloskelvin.crudrealm.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import br.com.karloskelvin.crudrealm.R;
import br.com.karloskelvin.crudrealm.adapter.ContatosAdapter;
import br.com.karloskelvin.crudrealm.model.Contato;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListarContatosActivity extends AppCompatActivity {

    private Realm realm;
    private RealmResults<Contato> contatos;
    private ListView lvContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        long inicioRealm = System.currentTimeMillis();
        realm = Realm.getDefaultInstance();

        contatos = realm.where(Contato.class).findAll();

        long fimRealm = System.currentTimeMillis();

        long tempoDecorrido = fimRealm - inicioRealm;

        lvContatos = (ListView) findViewById(R.id.lvContatos);
        lvContatos.setAdapter(new ContatosAdapter(this, contatos, true));

        Toast.makeText(this, this.getString(R.string.tempoDecorrido) + tempoDecorrido, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
