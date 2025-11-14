package com.example.cinema_estudantiltrabalhofinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Filme> listaDeFilmes;
    private List<Local> listaDeLocais;
    private List<Sessao> listaDeSessoes;

    private EditText editTitulo, editGenero, editDuracao, editClassificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDeFilmes = new ArrayList<>();
        listaDeLocais = new ArrayList<>();
        listaDeSessoes = new ArrayList<>();

        // Inicializar os campos
        inicializarCamposFilme();

        // Botões do menu principal (mantidos para outras funcionalidades)
        Button btnCadastrarFilme = findViewById(R.id.btnCadastrarFilme);
        Button btnCadastrarLocal = findViewById(R.id.btnCadastrarLocal);
        Button btnCadastrarSessao = findViewById(R.id.btnCadastrarSessao);
        Button btnListarFilmes = findViewById(R.id.btnListarFilmes);

        btnCadastrarFilme.setOnClickListener(v -> MenuAdm.cadastrarFilme(MainActivity.this, listaDeFilmes));

        btnCadastrarLocal.setOnClickListener(v -> MenuAdm.cadastrarLocal(MainActivity.this, listaDeLocais));

        btnCadastrarSessao.setOnClickListener(v -> MenuAdm.cadastrarSessao(MainActivity.this, listaDeSessoes, listaDeFilmes, listaDeLocais));

        btnListarFilmes.setOnClickListener(v -> listarFilmes());

        // Configurar os botões da tela principal
        configurarBotoesFilme();
    }

     private void inicializarCamposFilme() {
        filme_editTitulo = findViewById(R.id.filme_editTitulo);
        filme_editGenero = findViewById(R.id.filme_editGenero);
        filme_editDuracao = findViewById(R.id.filme_editDuracao);
        filme_editClassificacao = findViewById(R.id.filme_editClassificacao);
        
        filme_btnSalvar = findViewById(R.id.filme_btnSalvar);
        filme_btnCancelar = findViewById(R.id.filme_btnCancelar);
        filme_btnAdicionar = findViewById(R.id.filme_btnAdicionar);
        filme_btnExcluir = findViewById(R.id.filme_btnExcluir);
    }

    private void configurarBotoesFilme() {
        filme_btnSalvar.setOnClickListener(v -> salvarFilme());
        filme_btnCancelar.setOnClickListener(v -> limparCampos());
        filme_btnAdicionar.setOnClickListener(v -> limparCampos());
        filme_btnExcluir.setOnClickListener(v -> excluirFilme());
    }

    private void salvarFilme() {
        try {
            String titulo = filme_editTitulo.getText().toString();
            String generoStr = filme_editGenero.getText().toString();
            String duracaoStr = filme_editDuracao.getText().toString();
            String classificacaoStr = filme_editClassificacao.getText().toString();

            // Validações básicas
            if (titulo.isEmpty() || generoStr.isEmpty() || duracaoStr.isEmpty() || classificacaoStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            int duracao = Integer.parseInt(duracaoStr);
            int classificacao = Integer.parseInt(classificacaoStr);

            // Criar novo filme
            Filme filme = new Filme(titulo, genero, duracao, classificacao);
            listaDeFilmes.add(filme);

            Toast.makeText(this, "Filme salvo com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erro nos campos numéricos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void excluirFilme() {
        // Implementar lógica para excluir filme
        // Pode ser por busca ou seleção
        Toast.makeText(this, "Funcionalidade de exclusão", Toast.LENGTH_SHORT).show();
    }

    private void limparCampos() {
        filme_editTitulo.setText("");
        filme_editGenero.setText("");
        filme_editDuracao.setText("");
        filme_editClassificacao.setText("");
    }

    private void listarFilmes() {
        if (listaDeFilmes.isEmpty()) {
            Toast.makeText(this, "Não há filmes cadastrados!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder filmesListados = new StringBuilder("Filmes cadastrados:\n\n");
        for (Filme filme : listaDeFilmes) {
            filmesListados.append(filme.toString()).append("\n");
        }

        Toast.makeText(this, filmesListados.toString(), Toast.LENGTH_LONG).show();
    }
}

