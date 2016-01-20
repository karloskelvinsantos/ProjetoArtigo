package br.com.karloskelvin.crudsqlite.activitys;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import br.com.karloskelvin.crudsqlite.R;
import br.com.karloskelvin.crudsqlite.model.Contato;
import br.com.karloskelvin.crudsqlite.util.MyDataBaseHelper;

public class AdicionarContatoActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private MyDataBaseHelper myDataBaseHelper;
    private ContentValues contentValues;
    private Contato contato;

    private EditText edtNome, edtTel, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_contato);

        myDataBaseHelper = new MyDataBaseHelper(this);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtTel = (EditText) findViewById(R.id.edtTel);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

    }

    public void salvarContato(View view){
        sqLiteDatabase = myDataBaseHelper.getWritableDatabase();

        contato = new Contato();
        preencheContato();

        contentValues = new ContentValues();

        contentValues.put(MyDataBaseHelper.NOME, contato.getNome());
        contentValues.put(MyDataBaseHelper.TELEFONE, contato.getTelefone());
        contentValues.put(MyDataBaseHelper.EMAIL, contato.getEmail());

        long resultado = sqLiteDatabase.insert(MyDataBaseHelper.TABELA, null, contentValues);

        if (resultado == -1){
            Toast.makeText(this, "Erro Ao Salvar Contato! ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Contato Salvo! ", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void preencheContato(){
        contato.setNome(edtNome.getText().toString());
        contato.setTelefone(edtTel.getText().toString());
        contato.setEmail(edtEmail.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqLiteDatabase.close();
    }
}
