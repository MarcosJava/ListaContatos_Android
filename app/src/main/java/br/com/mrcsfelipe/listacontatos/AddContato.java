package br.com.mrcsfelipe.listacontatos;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.mrcsfelipe.listacontatos.database.DataBase;
import br.com.mrcsfelipe.listacontatos.dominio.ContatoDao;
import br.com.mrcsfelipe.listacontatos.dominio.entidade.Contato;

public class AddContato extends AppCompatActivity {


    private EditText edtNome,edtTelefone, edtEmail, edtEndereco, edtDataEspeciais, edtGrupos;

    private Spinner spnTipoEmail, spnTipoEndereco, spnTipoTelefone, spnTipoDataEspeciais;

    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoTelefone;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpTipoDataEspeciais;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private ContatoDao contatoDao;
    private Contato contato;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contato);


        this.contato = new Contato();

        this.edtNome = (EditText) findViewById(R.id.edt_nome);
        this.edtTelefone = (EditText) findViewById(R.id.edt_telefone);
        this.edtEmail = (EditText) findViewById(R.id.edt_email);
        this.edtEndereco = (EditText) findViewById(R.id.edt_endereco);
        this.edtDataEspeciais = (EditText) findViewById(R.id.edt_data_especiais);
        this.edtGrupos = (EditText) findViewById(R.id.edt_grupos);


        this.spnTipoEmail = (Spinner) findViewById(R.id.spn_tipo_email);
        this.spnTipoEndereco = (Spinner) findViewById(R.id.spn_tipo_endereco);
        this.spnTipoTelefone = (Spinner) findViewById(R.id.spn_tipo_telefone);
        this.spnTipoDataEspeciais = (Spinner) findViewById(R.id.spn_tipo_data_especiais);




        this.adpTipoEmail = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        this.adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.adpTipoEndereco = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        this.adpTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.adpTipoTelefone = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        this.adpTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.adpTipoDataEspeciais = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        this.adpTipoDataEspeciais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);




        this.spnTipoEmail.setAdapter(adpTipoEmail);
        this.spnTipoTelefone.setAdapter(adpTipoTelefone);
        this.spnTipoEndereco.setAdapter(adpTipoEndereco);
        this.spnTipoDataEspeciais.setAdapter(adpTipoDataEspeciais);



        this.adpTipoEmail.add("Casa");
        this.adpTipoEmail.add("Trabalho");
        this.adpTipoEmail.add("Outros");

        this.adpTipoTelefone.add("Celular");
        this.adpTipoTelefone.add("Trabalho");
        this.adpTipoTelefone.add("Casa");
        this.adpTipoTelefone.add("Principal");
        this.adpTipoTelefone.add("Fax Trabalho");
        this.adpTipoTelefone.add("Fax Casa");
        this.adpTipoTelefone.add("Pager");
        this.adpTipoTelefone.add("Outros");

        this.adpTipoEndereco.add("Casa");
        this.adpTipoEndereco.add("Trabalho");
        this.adpTipoEndereco.add("Outros");

        this.adpTipoDataEspeciais.add("Aniversario");
        this.adpTipoDataEspeciais.add("Data Comemorativa");
        this.adpTipoDataEspeciais.add("Outros");


        ExibeDataListenner exibeDataListenner = new ExibeDataListenner();
        this.edtDataEspeciais.setOnClickListener(exibeDataListenner);
        this.edtDataEspeciais.setOnFocusChangeListener(exibeDataListenner);


        try{
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            contatoDao = new ContatoDao(conn);

        }catch (SQLException ex){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Erro ao criar o banco !");
            dialog.setNeutralButton("OK", null);
            dialog.show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_contato, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.mn_acao_salvar:

                if(contato == null){
                        inserir();

                }else {

                }

                finish();
                break;

            case R.id.mn_acao_excluir:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inserir(){

        try{


            contato.setNome(edtNome.getText().toString());
            contato.setTelefone(edtTelefone.getText().toString());
            contato.setEmail(edtEmail.getText().toString());
            contato.setEndereco(edtEndereco.getText().toString());
            contato.setGrupos(edtGrupos.getText().toString());

            contato.setTipoTelefone(String.valueOf(spnTipoTelefone.getSelectedItemPosition()));
            contato.setTipoEmail(String.valueOf(spnTipoEmail.getSelectedItemPosition()));
            contato.setTipoEndereco(String.valueOf(spnTipoEndereco.getSelectedItemPosition()));
            contato.setTipoDataEspeciais(String.valueOf( spnTipoDataEspeciais.getSelectedItemPosition() ));


            contatoDao.inserir(contato);

            Toast.makeText(this,"Cadastrado com Sucesso", Toast.LENGTH_SHORT);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Ocorreu um erro ao inserir", Toast.LENGTH_SHORT);
        }

    }

    private void exibeData(){

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, new SelecionaDataListenner(), 2015, 4, 26);

        datePickerDialog.show();
    }

    private class ExibeDataListenner implements View.OnClickListener, View.OnFocusChangeListener{

        @Override
        public void onClick(View v) {
            exibeData();

        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            //Se tiver focus vai ativar a exibeData
            if(hasFocus)
                exibeData();
        }
    }

    private class SelecionaDataListenner implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);


            Date date = calendar.getTime();

            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String data = df.format(date);

            edtDataEspeciais.setText(data);
            contato.setDataEspeciais(date);
        }
    }
}
