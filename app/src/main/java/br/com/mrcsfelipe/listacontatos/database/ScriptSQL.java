package br.com.mrcsfelipe.listacontatos.database;

/**
 * Created by markFelipe on 28/10/15.
 */
public class ScriptSQL {

    public static String getCreateContato(){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS CONTATO( ");
        sqlBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("nome VARCHAR(200), ");
        sqlBuilder.append("telefone VARCHAR(14), ");
        sqlBuilder.append("tipoTelefone VARCHAR(1), ");
        sqlBuilder.append("email VARCHAR(255), ");
        sqlBuilder.append("tipoEmail VARCHAR(1), ");
        sqlBuilder.append("endereco VARCHAR(255), ");
        sqlBuilder.append("tipoEndereco VARCHAR(1), ");
        sqlBuilder.append("dataEspeciais DATE, ");
        sqlBuilder.append("tipoDataEspeciais VARCHAR(1), ");
        sqlBuilder.append("grupos VARCHAR(255) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

}
