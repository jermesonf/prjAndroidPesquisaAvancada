package com.example.prjloginpesquisaavancada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    //Declaração de variaveis
    EditText EditEntrada, EditNome, EditEnd, EditTel;
    TextView txtId;
    Spinner spDados;

    String entradaAntigo, nomeAntigo, endAntigo, telAntigo;

    //para o spinner funcionar é necessario vetor e adapter
    ArrayAdapter<String>ListaNome;//spinner so lê esse tipo de array
    String[]VetorNome;

    //Instanciando a classe Acessa
    Acessa objA = new Acessa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciando os objetos
        EditEntrada = findViewById(R.id.EditEntrada);
        EditNome = findViewById(R.id.editNome);
        EditEnd = findViewById(R.id.editEnd);
        EditTel = findViewById(R.id.editTel);
        txtId = findViewById(R.id.txtId);
        spDados = findViewById(R.id.spDados);

        //Evento para capturar a tecla
        EditEntrada.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                //Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
                String palavra = s.toString();
                pesquisa(palavra);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        //Evento para Spinner
        spDados.setOnItemSelectedListener
                (new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView parent, View view, int pos, long id) {
                        //programe aqui
                        String nomeL;
                        nomeL = parent.getItemAtPosition(pos).toString();

                        try{
                            objA.RS = objA.stmt.executeQuery("SELECT * FROM pessoal WHERE nome like '"+nomeL+"%' ");

                            if(objA.RS.next()){
                                String nome = objA.RS.getString("nome");
                                String ender = objA.RS.getString("endereco");
                                String tel = objA.RS.getString("telefone");
                                int id_pessoal = objA.RS.getInt("id_pessoal");
                                preencher(nome,ender,tel,id_pessoal);
                            }
                        }catch (SQLException e){}

                    }
                    public void onNothingSelected(AdapterView parent){}
                });

    }

    public void pesquisa(String n)
    {
        objA.entBanco(this);
        int i = 0;

        try{
            objA.RS = objA.stmt.executeQuery("SELECT * FROM pessoal WHERE nome LIKE '"+n+"%' ");

            i=0;
            while(objA.RS.next())
            {
                i++;
            }
        }catch (SQLException e){ }

        VetorNome = new String[i];

        try{
            objA.RS = objA.stmt.executeQuery("SELECT * FROM pessoal WHERE nome LIKE '"+n+"%' ");

            i=0;
            while (objA.RS.next())
            {
                VetorNome[i]=objA.RS.getString("nome");
                i++;
            }

            ListaNome = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,VetorNome);
            spDados.setAdapter(ListaNome);

        }catch (SQLException e){}
    }

    public void preencher(String nome, String endereco, String telefone, int id)
    {
        //atribuindo os parametros para os objetos
        EditNome.setText(nome);
        EditEnd.setText(endereco);
        EditTel.setText(telefone);
        txtId.setText("" + id);


    }





    public void alterar(View v)
    {
        String nome,end,tel;
        int idpessoal;

        nome = EditNome.getText().toString();
        end = EditEnd.getText().toString();
        tel = EditTel.getText().toString();

        idpessoal = Integer.parseInt(txtId.getText().toString());

        try{
            int result = objA.stmt.executeUpdate("UPDATE pessoal SET nome = '"+nome+"', endereco='"+end+"', telefone='"+tel+"' WHERE id_pessoal="+idpessoal+" " );
            if(result > 0){
                Toast.makeText(getApplicationContext(), "Dados alterados com sucesso", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Não foi possivel alterar.", Toast.LENGTH_LONG).show();
            }

        }catch (SQLException ex)
        {
        }

    }

}// FIM