package br.com.alura.literalura.view;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BuscaLivro {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public BuscaLivro(LivroRepository livroRepository, AutorRepository autorRepository, ObjectMapper objectMapper) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.objectMapper = objectMapper;
    }

    public void buscaPorTitulo(Scanner scanner) throws IOException {
        System.out.print("Digite o título do livro: ");
        String tituloLivro = scanner.nextLine();
        String url = "https://gutendex.com/books?search=" + tituloLivro.replace(" ", "+");

        JsonNode rootNode = objectMapper.readTree(new URL(url));

        if (rootNode.has("results") && rootNode.get("results").isArray() && !rootNode.get("results").isEmpty()) {
            for (JsonNode livroNode : rootNode.get("results")) {
                Livro livro = new Livro();
                livro.setId(livroNode.get("id").asLong());
                livro.setTitulo(livroNode.get("title").asText());
                livro.setIdioma(livroNode.get("languages").get(0).asText());
                livro.setQuantidadeDownloads(livroNode.get("download_count").asInt());

                Set<Autor> autores = new HashSet<>();
                for (JsonNode autorNode : livroNode.get("authors")) {
                    String nomeAutor = autorNode.get("name").asText();

                    Autor autor = autorRepository.findByNome(nomeAutor);
                    if (autor == null) {
                        autor = new Autor();
                        autor.setNome(nomeAutor);
                        autor.setAnoNascimento(autorNode.has("birth_year") ? autorNode.get("birth_year").asInt() : 0);
                        autor.setAnoFalecimento(autorNode.has("death_year") ? autorNode.get("death_year").asInt() : 0);
                    }

                    autores.add(autor);
                }

                livro.setAutores(autores);
                livroRepository.save(livro);

                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor(es): " + livro.getAutores());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Quantidade de Downloads: " + livro.getQuantidadeDownloads());
                System.out.println();
            }
        } else {
            System.out.println("Nenhum livro encontrado para a consulta: " + tituloLivro);
        }
    }

    @Transactional(readOnly = true)
    public void buscaPorIdioma(Scanner scanner) {
        List<String> idiomasDisponiveis = livroRepository.findDistinctIdiomas();

        if (idiomasDisponiveis.isEmpty()) {
            System.out.println("Nenhum livro cadastrado ainda.");
            return;
        }

        System.out.println("\n--- Busca Por Idioma ---");
        System.out.println("Idiomas disponíveis:");
        for (int i = 0; i < idiomasDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + idiomasDisponiveis.get(i));
        }

        System.out.print("Escolha um idioma pelo número: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha >= 1 && escolha <= idiomasDisponiveis.size()) {
            String idiomaLivro = idiomasDisponiveis.get(escolha - 1);
            List<Livro> livros = livroRepository.findByIdioma(idiomaLivro);

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado para o idioma: " + idiomaLivro);
            } else {
                System.out.println("\nLivros encontrados para o idioma " + idiomaLivro + ":");
                for (Livro livro : livros) {
                    System.out.println("Título: " + livro.getTitulo());

                    String autores = livro.getAutores().stream()
                            .map(Autor::getNome)
                            .collect(Collectors.joining(" & "));
                    System.out.println("Autor(es): " + autores);

                    System.out.println("Quantidade de Downloads: " + livro.getQuantidadeDownloads());
                    System.out.println("----------------------");
                }
            }
        } else {
            System.out.println("Opção inválida, tente novamente.");
        }
    }

    public void buscaTodos() {
        List<Livro> livros = livroRepository.findAllWithAutores();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado ainda.");
        } else {
            System.out.println("\nTodos os livros cadastrados:");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                String autores = livro.getAutores().stream()
                        .map(Autor::getNome)
                        .collect(Collectors.joining(" & "));
                System.out.println("Autor(es): " + autores);
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Quantidade de Downloads: " + livro.getQuantidadeDownloads());
                System.out.println("----------------------");
            }
        }
    }


    public void buscaMaisBaixados() {
        List<Livro> livros = livroRepository.findTop10ByOrderByQuantidadeDownloadsDesc();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado ainda.");
        } else {
            System.out.println("\nTop 10 livros mais baixados:");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                String autores = livro.getAutores().stream()
                        .map(Autor::getNome)
                        .collect(Collectors.joining(", "));
                System.out.println("Autor(es): " + autores);
                System.out.println("Quantidade de Downloads: " + livro.getQuantidadeDownloads());
                System.out.println("----------------------");
            }
        }
    }

}
