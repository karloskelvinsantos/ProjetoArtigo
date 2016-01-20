package br.com.karloskelvin.crudrealm.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.karloskelvin.crudrealm.R;
import br.com.karloskelvin.crudrealm.model.Contato;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class AdicionarContatoActivity extends AppCompatActivity {

    private Realm realm;
    private Contato contato;
    private RealmResults<Contato> contatos;

    private EditText edtNome, edtTel, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_contato);

        realm = Realm.getDefaultInstance();

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTel = (EditText) findViewById(R.id.edtTel);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

    }

    public void salvarContato(View view){
        contatos =  realm.where(Contato.class).findAll();

        contatos.sort("id", Sort.DESCENDING);
        long id = contatos.size() == 0 ? 1 : contatos.get(0).getId() + 1;

        realm.beginTransaction();

        contato = new Contato();
        contato.setId(id);
        contato.setNome(edtNome.getText().toString());
        contato.setTelefone(edtTel.getText().toString());
        contato.setEmail(edtEmail.getText().toString());

        realm.copyToRealm(contato);

        realm.commitTransaction();

        Toast.makeText(this, "Contato Salvo! ", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
