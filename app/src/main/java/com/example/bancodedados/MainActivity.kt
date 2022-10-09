package com.example.bancodedados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            // Criar banco de dados
            val bancoDados = openOrCreateDatabase("DB_FORNECEDORES", MODE_PRIVATE, null)

            // Criar uma tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS fornecedores(nome VARCHAR, telefone INT(4))")

            //Inseir dados em uma tabela
//            bancoDados.execSQL("INSERT INTO fornecedores(nome,telefone) VALUES ('Cia ultragaza S.A', 1010)")
//            bancoDados.execSQL("INSERT INTO fornecedores(nome,telefone) VALUES ('Academia Gourmet', 2020)")

            // Consulta para o cursor buscar
            val consulta = "SELECT nome,telefone FROM fornecedores WHERE nome = 'Cia ultragaza S.A'"

            // Criação  do cursor
            val cursor = bancoDados.rawQuery(consulta, null)

            //Recuperar os indices da nossa tabela
            val indiceNome = cursor.getColumnIndex("nome")
            val indiceTelefone = cursor.getColumnIndex("telefone")
            cursor.moveToFirst() // volta para o primeiro item da lista

            while (cursor != null){

                val nome = cursor.getString(indiceNome)
                val telefone = cursor.getString(indiceTelefone)
                Log.i("RESULTADO", "/nome: $nome/ telefone: $telefone")

                cursor.moveToNext()// Vai para o próximo item da lista
            }

        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}