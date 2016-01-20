package br.com.karloskelvin.crudsqlite.activitys;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.karloskelvin.crudsqlite.R;
import br.com.karloskelvin.crudsqlite.model.Contato;
import br.com.karloskelvin.crudsqlite.util.MyDataBaseHelper;

public class InserirCargaDadosActivity extends AppCompatActivity {

    private EditText edtQtdRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_carga_dados);

        edtQtdRegistros = (EditText) findViewById(R.id.txtQtdRegistros);
    }

    public void cargaDados(View view){
            String qtdRegistros = edtQtdRegistros.getText().toString();

            new SQLiteAsyncTask(this).execute(qtdRegistros);
    }

    public class SQLiteAsyncTask extends AsyncTask<String, Integer, Long> {


        private ProgressDialog progressDialog;
        private Context context;
        private MyDataBaseHelper helper;
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
        protected Long doInBackground(String... modeloTasks) {

                helper = new MyDataBaseHelper(context);
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                contato = new Contato();

                long inicioSQLite = System.currentTimeMillis();

                for (int i = 0; i < Integer.valueOf(modeloTasks[0]); i++) {
                    contato.setNome("teste" + i);
                    contato.setTelefone("teste" + i);
                    values.put("nome", contato.getNome());
                    values.put("telefone", contato.getTelefone());
                    db.insert("contato", null, values);
                    values.clear();
                }

                long fimSQLite = System.currentTimeMillis();

                tempDecorrido = fimSQLite - inicioSQLite;

                Log.d("TEMPO DECORRIDO SQLITE", Long.toString(tempDecorrido));

            return tempDecorrido;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            progressDialog.dismiss();
           Toast.makeText(context, context.getString(R.string.tempoDecorrido) + aLong.intValue(), Toast.LENGTH_SHORT).show();
            helper.close();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
