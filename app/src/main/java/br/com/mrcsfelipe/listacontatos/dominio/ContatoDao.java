package br.com.mrcsfelipe.listacontatos.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.Date;

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

    public ArrayAdapter<Contato> buscaContatos(Context context){

        ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("CONTATO", null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do {

                Contato contato = new Contato();
                contato.setNome( cursor.getString(1));
                contato.setTelefone(cursor.getString(2));
                contato.setTipoTelefone(cursor.getString(3));
                contato.setEmail(cursor.getString(4));
                contato.setTipoEmail(cursor.getString(5));
                contato.setEmail(cursor.getString(6));
                contato.setTipoEmail(cursor.getString(7));
                contato.setDataEspeciais(new Date(cursor.getLong(8)));
                contato.setTipoDataEspeciais(cursor.getString(9));
                contato.setGrupos(cursor.getString(10));

                //String telefone = cursor.getString(1);
                adpContatos.add(contato);

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
