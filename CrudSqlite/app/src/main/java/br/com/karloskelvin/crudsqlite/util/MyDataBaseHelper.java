package br.com.karloskelvin.crudsqlite.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Karlos Kelvin on 07/01/16.
 * Desenvolvedor de Sistemas - UFCA
 * Analista e Desenvolvedor de Sistemas.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String NOME_BD = "crudsqlite.db";
    public static final String TABELA = "contato";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";
    public static final String EMAIL = "email";
    public static final int VERSAO = 1;

    public MyDataBaseHelper(Context context){
        super(context, NOME_BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " +TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " text, "
                + TELEFONE + " text, "
                + EMAIL + " text"
                +")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(sqLiteDatabase);
    }

}
