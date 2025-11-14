package com.example.cinema_estudantiltrabalhofinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MenuAdm {
    // Método para cadastrar um filme
    public static void cadastrarFilme(Context context, List<Filme> listaDeFilmes) {
        // Criar os campos para entrada de dados
        final EditText editTextTitulo = new EditText(context);
        editTextTitulo.setHint("Título do filme");

        final EditText editTextDuracao = new EditText(context);
        editTextDuracao.setHint("Duração (em minutos)");
        editTextDuracao.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        final EditText editTextClassificacao = new EditText(context);
        editTextClassificacao.setHint("Classificação");
        editTextClassificacao.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        // Spinner para seleção de gênero
        String[] generos = {"Ação", "Anime", "Comédia", "Drama", "Ficção Científica", "Romance", "Suspense", "Terror"};
        final Spinner spinnerGeneros = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGeneros.setAdapter(adapter);

        // Criar o AlertDialog para exibir os campos
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cadastrar Filme");

        // Layout para os campos
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editTextTitulo);
        layout.addView(editTextDuracao);
        layout.addView(editTextClassificacao);
        layout.addView(spinnerGeneros);

        builder.setView(layout);

        builder.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Capturar os dados inseridos
                String titulo = editTextTitulo.getText().toString();
                int duracao = Integer.parseInt(editTextDuracao.getText().toString());
                int classificacao = Integer.parseInt(editTextClassificacao.getText().toString());
                int generoSelecionado = spinnerGeneros.getSelectedItemPosition() + 1; // Gênero selecionado

                // Criar objeto Filme e adicionar à lista
                Filme filme = new Filme(titulo, generoSelecionado, duracao, classificacao);
                listaDeFilmes.add(filme);

                // Feedback para o usuário
                Toast.makeText(context, "Filme cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", null);

        builder.show();
    }

    // Método para cadastrar um local
    public static void cadastrarLocal(Context context, List<Local> listaDeLocais) {
        final EditText editTextBloco = new EditText(context);
        editTextBloco.setHint("Bloco (ex: A, B, C)");

        final EditText editTextSala = new EditText(context);
        editTextSala.setHint("Sala (número)");
        editTextSala.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editTextBloco);
        layout.addView(editTextSala);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cadastrar Local");
        builder.setView(layout);

        builder.setPositiveButton("Cadastrar", (dialog, which) -> {
            String bloco = editTextBloco.getText().toString();
            int sala = Integer.parseInt(editTextSala.getText().toString());

            Local local = new Local(sala, bloco);
            listaDeLocais.add(local);

            Toast.makeText(context, "Local cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }


    // Método para cadastrar uma sessão
    public static void cadastrarSessao(Context context, List<Sessao> listaDeSessoes, List<Filme> listaDeFilmes, List<Local> listaDeLocais) {
        if (listaDeFilmes.isEmpty() || listaDeLocais.isEmpty()) {
            Toast.makeText(context, "Cadastre pelo menos um filme e um local antes!", Toast.LENGTH_LONG).show();
            return;
        }

        final EditText editTextData = new EditText(context);
        editTextData.setHint("Data (dd/mm/aaaa)");

        final EditText editTextHora = new EditText(context);
        editTextHora.setHint("Hora (hh:mm)");

        Spinner spinnerFilmes = new Spinner(context);
        ArrayAdapter<Filme> adapterFilmes = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listaDeFilmes);
        adapterFilmes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilmes.setAdapter(adapterFilmes);

        Spinner spinnerLocais = new Spinner(context);
        ArrayAdapter<Local> adapterLocais = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listaDeLocais);
        adapterLocais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocais.setAdapter(adapterLocais);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editTextData);
        layout.addView(editTextHora);
        layout.addView(spinnerFilmes);
        layout.addView(spinnerLocais);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cadastrar Sessão");
        builder.setView(layout);

        builder.setPositiveButton("Cadastrar", (dialog, which) -> {
            try {
                String dataStr = editTextData.getText().toString();
                String hora = editTextHora.getText().toString();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                java.util.Date data = sdf.parse(dataStr);

                Filme filmeSelecionado = (Filme) spinnerFilmes.getSelectedItem();
                Local localSelecionado = (Local) spinnerLocais.getSelectedItem();

                Sessao sessao = new Sessao(data, hora, listaDeLocais.indexOf(localSelecionado) + 1, listaDeFilmes.indexOf(filmeSelecionado) + 1);
                listaDeSessoes.add(sessao);

                Toast.makeText(context, "Sessão cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Erro ao cadastrar sessão!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    

}

