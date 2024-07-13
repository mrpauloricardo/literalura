package br.com.alura.literalura.view;

import br.com.alura.literalura.model.Livro;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BuscaLivro {

    public String buscaPorTitulo(Scanner scanner) throws IOException {
        System.out.print("Digite o título do livro: ");
        String tituloLivro = scanner.nextLine();
        String url = "https://gutendex.com/books?search=" + tituloLivro.replace(" ", "+");
        return retornoConsulta(tituloLivro, url);
    }

    public String buscaPorAutor(Scanner scanner) throws IOException {
        System.out.print("Digite o autor do livro: ");
        String autorLivro = scanner.nextLine();
        String url = "https://gutendex.com/books?search=" + autorLivro.replace(" ", "+");
        return retornoConsulta(autorLivro, url);
    }

    public String buscaPorIdioma(Scanner scanner) throws IOException {
        System.out.print("Digite o idioma do livro: ");
        String idiomaLivro = scanner.nextLine();
        String url = "https://gutendex.com/books?languages=" + idiomaLivro.replace(" ", "+");
        return retornoConsulta(idiomaLivro, url);
    }

    public String retornoConsulta(String consulta, String url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new URL(url));
        Set<String> titulosExibidos = new HashSet<>();

        if (rootNode.has("results") && rootNode.get("results").isArray() && !rootNode.get("results").isEmpty()) {
            for (JsonNode livroNode : rootNode.get("results")) {
                String titulo = livroNode.get("title").asText();
                if (!titulosExibidos.contains(titulo)) {
                    Livro livro = new Livro();
                    livro.setId(livroNode.get("id").asLong());
                    livro.setTitulo(titulo);

                    StringBuilder autores = new StringBuilder();
                    for (JsonNode autorNode : livroNode.get("authors")) {
                        autores.append(autorNode.get("name").asText()).append(", ");
                    }
                    if (autores.length() > 0) {
                        autores.setLength(autores.length() - 2);
                    }
                    livro.setAutor(autores.toString());

                    livro.setIdioma(livroNode.get("languages").get(0).asText());
                    livro.setQuantidadeDownloads(livroNode.get("download_count").asInt());

                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Autor: " + livro.getAutor());
                    System.out.println("Idioma: " + livro.getIdioma());
                    System.out.println("Quantidade de Downloads: " + livro.getQuantidadeDownloads());
                    System.out.println();

                    titulosExibidos.add(titulo);
                }
            }
        } else {
            System.out.println("Nenhum livro encontrado para a consulta: " + consulta);
        }
        return consulta;
    }
}
