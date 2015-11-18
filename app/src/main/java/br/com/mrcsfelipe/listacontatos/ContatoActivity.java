package br.com.mrcsfelipe.listacontatos;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import br.com.mrcsfelipe.listacontatos.dominio.ContatoDao;
import br.com.mrcsfelipe.listacontatos.database.DataBase;
import br.com.mrcsfelipe.listacontatos.dominio.entidade.Contato;

public class ContatoActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContatos;

    private ArrayAdapter<Contato> adpContatos;

    private DataBase dataBase;
    private SQLiteDatabase conn;

    private ContatoDao contatoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        btnAdicionar = (ImageButton) findViewById(R.id.img_btn_add);
        edtPesquisa = (EditText) findViewById(R.id.edt_pesquisa);
        lstContatos = (ListView) findViewById(R.id.lst_view_contatos);

        btnAdicionar.setOnClickListener(this);

        try{

            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            contatoDao = new ContatoDao(conn);


            adpContatos = contatoDao.buscaContatos(this);
            lstContatos.setAdapter(adpContatos);

        }catch (SQLException ex){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao criar o banco !");
            dialog.setNeutralButton("OK", null);
            dialog.show();
        }


    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(ContatoActivity.this, AddContato.class);
        startActivityForResult(intent, 0);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        adpContatos = contatoDao.buscaContatos(this);
        lstContatos.setAdapter(adpContatos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
