package br.com.karloskelvin.exemplosqlite;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.karloskelvin.exemplosqlite.model.Contato;
import br.com.karloskelvin.exemplosqlite.util.DatabaseHelper;
import br.com.karloskelvin.exemplosqlite.model.ModeloTask;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Button btnRealm;
    private Button btnSQLite;
    private EditText edtQtdRegistros;

    private Contato contato;
    private long qtdRegistros;

    //SQLite
    private DatabaseHelper helper;

    //REALM
    private Realm realm;
    private RealmResults<Contato> contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRealm = (Button) findViewById(R.id.btn_realm);
        btnSQLite = (Button) findViewById(R.id.btn_sqlite);
        edtQtdRegistros = (EditText) findViewById(R.id.txtQtdRegistros);

    }

    public void salvarSQLite(View view){

        if (edtQtdRegistros.getText().equals("")){
            Toast.makeText(this, "Preencha o campo Quantidade de Registros", Toast.LENGTH_SHORT).show();
        }else{

            qtdRegistros = Integer.valueOf(edtQtdRegistros.getText().toString());

            new SQLiteAsyncTask(this).execute(new ModeloTask(qtdRegistros, "sqlite"));

        }
    }

    public void salvarRealm(View view){
        if (edtQtdRegistros.getText().equals("")){
            Toast.makeText(this, "Preencha o campo Quantidade de Registros", Toast.LENGTH_SHORT).show();
        }else {

            qtdRegistros = Integer.valueOf(edtQtdRegistros.getText().toString());

            new SQLiteAsyncTask(this).execute(new ModeloTask(qtdRegistros, "realm"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
        realm.close();
    }

    public class SQLiteAsyncTask extends AsyncTask<ModeloTask, Integer,   Long> {


        private ProgressDialog progressDialog;
        private Context context;
        private DatabaseHelper helper;
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
        protected Long doInBackground(ModeloTask... modeloTasks) {

            if (modeloTasks[0].bd.equalsIgnoreCase("sqlite")) {

                helper = new DatabaseHelper(context);
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                contato = new Contato();

                long inicioSQLite = System.currentTimeMillis();

                for (int i = 0; i < modeloTasks[0].qtdRegistro; i++) {
                    contato.setNome("teste" + i);
                    contato.setTelefone("teste" + i);
                    values.put("nome", contato.getNome());
                    values.put("telefone", contato.getTelefone());
                    db.insert("contato", null, values);
                    values.clear();
                }

                long fimSQLite = System.currentTimeMillis();

                tempDecorrido = (fimSQLite - inicioSQLite);

                Log.d("TEMPO DECORRIDO SQLITE", "" + tempDecorrido);



            }else if (modeloTasks[0].bd.equalsIgnoreCase("realm")) {

                realm = Realm.getInstance(context);

                contatos = realm.where(Contato.class).findAll();

                contatos.sort("id", RealmResults.SORT_ORDER_DESCENDING);
                long id = contatos.size() == 0 ? 1 : contatos.get(0).getId() + 1;

                realm.beginTransaction();

                long inicioRealm = System.currentTimeMillis();

                for (int i = 1; i <= modeloTasks[0].qtdRegistro; i++) {
                    contato = new Contato();
                    contato.setId(id++);
                    contato.setNome("teste" + i);
                    contato.setTelefone("teste" + i);
                    realm.copyToRealm(contato);
                }

                realm.commitTransaction();

                long fimRealm = System.currentTimeMillis();

                tempDecorrido = (fimRealm - inicioRealm);

                Log.d("TEMPO DECORRIDO REALM: ", "" + tempDecorrido);

            }

            return tempDecorrido;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            progressDialog.dismiss();
            TextView txtResultado = (TextView) findViewById(R.id.txtResultado);
            txtResultado.setText("Tempo Decorrido (ms): " + aLong.intValue());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
