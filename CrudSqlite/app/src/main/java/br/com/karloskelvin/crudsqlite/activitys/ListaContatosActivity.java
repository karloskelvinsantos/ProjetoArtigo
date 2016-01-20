package br.com.karloskelvin.crudsqlite.activitys;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.com.karloskelvin.crudsqlite.R;
import br.com.karloskelvin.crudsqlite.util.MyDataBaseHelper;

public class ListaContatosActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;
    private MyDataBaseHelper myDataBaseHelper;
    private ListView lvContatos;

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        myDataBaseHelper = new MyDataBaseHelper(this);


        String[] campos = {MyDataBaseHelper.ID, MyDataBaseHelper.NOME};
        int[] idViews = new int[] {android.R.id.text1, android.R.id.text2};

        long inicioSQLite = System.currentTimeMillis();

        sqLiteDatabase = myDataBaseHelper.getReadableDatabase();

        cursor = sqLiteDatabase.query(MyDataBaseHelper.TABELA, campos, null, null, null, null, null, null);

        long fimSQLite = System.currentTimeMillis();

        long tempoDecorrido = fimSQLite - inicioSQLite;

        if (cursor != null){
            cursor.moveToFirst();
        }

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), android.R.layout.simple_list_item_2, cursor, campos, idViews, 0);

        lvContatos = (ListView) findViewById(R.id.lvContatos);
        lvContatos.setAdapter(adapter);

        Toast.makeText(this, this.getString(R.string.tempoDecorrido) + tempoDecorrido, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqLiteDatabase.close();
    }
}
