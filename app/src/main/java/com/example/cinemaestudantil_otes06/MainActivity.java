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
        inicializarCamposLocal();
        inicializarCamposSessao();

        // Botões do menu principal (mantidos para outras funcionalidades)
        Button btnCadastrarFilme = findViewById(R.id.btnCadastrarFilme);
        Button btnCadastrarLocal = findViewById(R.id.btnCadastrarLocal);
        Button btnCadastrarSessao = findViewById(R.id.btnCadastrarSessao);
        Button btnListarFilmes = findViewById(R.id.btnListarFilmes);
        Button btnListarLocais = findViewById(R.id.btnListarLocais);
        Button btnListarSessoes = findViewById(R.id.btnListarSessoes);

        btnCadastrarFilme.setOnClickListener(v -> MenuAdm.cadastrarFilme(MainActivity.this, listaDeFilmes));

        btnCadastrarLocal.setOnClickListener(v -> MenuAdm.cadastrarLocal(MainActivity.this, listaDeLocais));

        btnCadastrarSessao.setOnClickListener(v -> MenuAdm.cadastrarSessao(MainActivity.this, listaDeSessoes, listaDeFilmes, listaDeLocais));

        btnListarFilmes.setOnClickListener(v -> listarFilmes());

        // Configurar os botões da tela principal
        configurarBotoesFilme();
        configurarBotoesLocal();
        configurarBotoesSessao();
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
        filme_btnCancelar.setOnClickListener(v -> limparCamposFilme());
        filme_btnAdicionar.setOnClickListener(v -> limparCamposFilme());
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
            limparCamposFilme();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erro nos campos numéricos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void excluirFilme() {
        // Implementar lógica para excluir filme
        // Pode ser por busca ou seleção
        Toast.makeText(this, "Funcionalidade de exclusão", Toast.LENGTH_SHORT).show();
    }

    private void limparCamposFilme() {
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

    private void inicializarCamposLocal() {
        local_editBloco = findViewById(R.id.local_editBloco);
        local_editSala = findViewById(R.id.local_editSala);
        
        local_btnSalvar = findViewById(R.id.local_btnSalvar);
        local_btnCancelar = findViewById(R.id.local_btnCancelar);
        local_btnAdicionar = findViewById(R.id.local_btnAdicionar);
        local_btnExcluir = findViewById(R.id.local_btnExcluir);
    }

    private void configurarBotoesLocal() {
        local_btnSalvar.setOnClickListener(v -> salvarLocal());
        local_btnCancelar.setOnClickListener(v -> limparCamposLocal());
        local_btnAdicionar.setOnClickListener(v -> limparCamposLocal());
        local_btnExcluir.setOnClickListener(v -> excluirLocal());
    }

    private void salvarLocal() {
        try {
            String bloco = local_editBloco.getText().toString();
            String sala = local_editSala.getText().toString();

            // Validações básicas
            if (bloco.isEmpty() || sala.isEmpty() || classificacaoStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Criar novo local
            Local local = new Local(sala, bloco);
            listaDeLocais.add(local);

            Toast.makeText(this, "Local salvo com sucesso!", Toast.LENGTH_SHORT).show();
            limparCamposLocal();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erro nos campos numéricos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void excluirLocal() {
        // Implementar lógica para excluir local
        // Pode ser por busca ou seleção
        Toast.makeText(this, "Funcionalidade de exclusão", Toast.LENGTH_SHORT).show();
    }

    private void limparCamposLocal() {
        local_editBloco.setText("");
        local_editSala.setText("");
    }

    private void listarLocais() {
        if (listaDeLocais.isEmpty()) {
            Toast.makeText(this, "Não há locais cadastrados!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder locaisListados = new StringBuilder("Locais cadastrados:\n\n");
        for (Local local : listaDeLocais) {
            locaisListados.append(local.toString()).append("\n");
        }

        Toast.makeText(this, locaisListados.toString(), Toast.LENGTH_LONG).show();
    }

    private void inicializarCamposSessao() {
        sessao_editData = findViewById(R.id.sessao_editData);
        sessao_editHora = findViewById(R.id.sessao_editHora);
        sessao_editLocal = findViewById(R.id.sessao_editLocal);
        sessao_editFilme = findViewById(R.id.sessao_editFilme);
        
        sessao_btnSalvar = findViewById(R.id.sessao_btnSalvar);
        sessao_btnCancelar = findViewById(R.id.sessao_btnCancelar);
        sessao_btnAdicionar = findViewById(R.id.sessao_btnAdicionar);
        sessao_btnExcluir = findViewById(R.id.sessao_btnExcluir);
    }

    private void configurarBotoesSessao() {
        sessao_btnSalvar.setOnClickListener(v -> salvarSessao());
        sessao_btnCancelar.setOnClickListener(v -> limparCamposSessao());
        sessao_btnAdicionar.setOnClickListener(v -> limparCamposSessao());
        sessao_btnExcluir.setOnClickListener(v -> excluirSessao());
    }

    private void salvarSessao() {
        try {
            String data = sessao_editData.getText().toString();
            String hora = sessao_editHora.getText().toString();
            String local = sessao_editLocal.getText().toString();
            String filme = sessao_editFilme.getText().toString();

            // Validações básicas
            if (data.isEmpty() || hora.isEmpty() || local.isEmpty() || filme.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            int duracao = Integer.parseInt(duracaoStr);
            int classificacao = Integer.parseInt(classificacaoStr);

            // Criar novo sessao
            Sessao sessao = new Sessao(data, hora, local, filme);
            listaDeSessoes.add(sessao);

            Toast.makeText(this, "Sessão salvo com sucesso!", Toast.LENGTH_SHORT).show();
            limparCamposSessao();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erro nos campos numéricos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void excluirSessao() {
        // Implementar lógica para excluir sessao
        // Pode ser por busca ou seleção
        Toast.makeText(this, "Funcionalidade de exclusão", Toast.LENGTH_SHORT).show();
    }

    private void limparCamposSessao() {
        sessao_editData.setText("");
        sessao_editHora.setText("");
        sessao_editLocal.setText("");
        sessao_editFilme.setText("");
    }

    private void listarSessoes() {
        if (listaDeSessoes.isEmpty()) {
            Toast.makeText(this, "Não há sessões cadastradas!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder sessoesListadas = new StringBuilder("Sessões cadastradas:\n\n");
        for (Sessao sessao : listaDeSessoes) {
            sessoesListadas.append(sessao.toString()).append("\n");
        }

        Toast.makeText(this, sessoesListadas.toString(), Toast.LENGTH_LONG).show();
    }
}