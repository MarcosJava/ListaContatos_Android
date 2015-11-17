package br.com.mrcsfelipe.listacontatos.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import br.com.mrcsfelipe.listacontatos.database.DataBase;
import br.com.mrcsfelipe.listacontatos.dominio.entidade.Contato;

/**
 * Created by markFelipe on 28/10/15.
 */
public class ContatoDao {


    private DataBase dataBase;
    private SQLiteDatabase conn;


    public ContatoDao(SQLiteDatabase conn) {
        this.conn = conn;
    }

    public ArrayAdapter<String> buscaContatos(Context context){

        ArrayAdapter<String> adpContatos = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("CONTATO", null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do {

                String telefone = cursor.getString(1);
                adpContatos.add(telefone);

            } while (cursor.moveToNext());


        }

        return adpContatos;
    }


    public void inserir(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("telefone", contato.getTelefone());
        values.put("tipoTelefone", contato.getTipoTelefone());
        values.put("email", contato.getEmail());
        values.put("tipoEmail", contato.getTipoEmail());
        values.put("endereco", contato.getEndereco());
        values.put("tipoEndereco", contato.getTipoEndereco());
        values.put("dataEspeciais", contato.getDataEspeciais().getTime());
        values.put("tipoDataEspeciais", contato.getTipoDataEspeciais());
        values.put("grupos", contato.getGrupos());

        long id = conn.insertOrThrow("CONTATO", null, values);

    }

}
