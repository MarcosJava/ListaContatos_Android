package br.com.mrcsfelipe.listacontatos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by markFelipe on 28/10/15.
 */
public class DataBase extends SQLiteOpenHelper {


    public final static String DATABASE = "AGENDA";
    public final static int VERSION = 1;

    public DataBase(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptSQL.getCreateContato());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
