package br.com.karloskelvin.crudrealm.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.karloskelvin.crudrealm.R;
import br.com.karloskelvin.crudrealm.model.Contato;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class AdicionarCargaDadosActivity extends AppCompatActivity {

    private Realm realm;
    private RealmResults<Contato> contatos;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_carga_dados);

        editText = (EditText) findViewById(R.id.txtQtdRegistros);

    }

    public void salvarCarga(View view){

        new SQLiteAsyncTask(this).execute(editText.getText().toString());
    }

    public class SQLiteAsyncTask extends AsyncTask<String, Integer, Long> {

        private ProgressDialog progressDialog;
        private Context context;
        private long tempDecorrido;
        private Contato contato;

        public SQLiteAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Processando...");
            progressDialog.show();
        }

        @Override
        protected Long doInBackground(String... qtdRegistros) {

            realm = Realm.getDefaultInstance();

            contatos = realm.where(Contato.class).findAll();

            contatos.sort("id", Sort.DESCENDING);
            long id = contatos.size() == 0 ? 1 : contatos.get(0).getId() + 1;

            realm.beginTransaction();

            long inicioRealm = System.currentTimeMillis();

            for (int i = 1; i <= Integer.valueOf(qtdRegistros[0]); i++) {
                contato = new Contato();
                contato.setId(id++);
                contato.setNome("teste" + i);
                contato.setTelefone("teste" + i);
                realm.copyToRealm(contato);
            }

            realm.commitTransaction();

            long fimRealm = System.currentTimeMillis();

            tempDecorrido = fimRealm - inicioRealm;

            Log.d("TEMPO DECORRIDO REALM: ", Long.toString(tempDecorrido));

            return tempDecorrido;

        }


        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            progressDialog.dismiss();
            Toast.makeText(context, context.getString(R.string.tempoDecorrido) + aLong.intValue(), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

    }
}
