package br.com.alura.literalura.view;

import br.com.alura.literalura.model.Autor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BuscaAutor {

    public String buscaPorNome(Scanner scanner) throws IOException {
        System.out.print("Digite o nome do autor: ");
        String nomeAutor = scanner.nextLine();
        String url = "https://gutendex.com/books?search=" + nomeAutor.replace(" ", "+");
        return retornoConsulta(nomeAutor, url, 0, 0, "nome");
    }

    public String buscaPorAnoNascimento(Scanner scanner) throws IOException {
        System.out.print("Digite o ano de nascimento do autor: ");
        int anoNascimento = scanner.nextInt();
        scanner.nextLine();
        String url = "https://gutendex.com/books?author_year_start=" + anoNascimento;
        return retornoConsulta(String.valueOf(anoNascimento), url, anoNascimento, 0, "nascimento");
    }

    public String buscaPorAnoFalecimento(Scanner scanner) throws IOException {
        System.out.print("Digite o ano de falecimento do autor: ");
        int anoFalecimento = scanner.nextInt();
        scanner.nextLine();
        String url = "https://gutendex.com/books?author_year_end=" + anoFalecimento;
        return retornoConsulta(String.valueOf(anoFalecimento), url, 0, anoFalecimento, "falecimento");
    }

    public String buscaPorIntervalo(Scanner scanner) throws IOException {
        System.out.print("Digite o ano em que o autor deve estar vivo: ");
        int intervalo = scanner.nextInt();
        scanner.nextLine();
        String url = "https://gutendex.com/books?author_year_start=" + intervalo + "&author_year_end=" + intervalo;
        return retornoConsulta(String.valueOf(intervalo), url, intervalo, intervalo, "nome");
    }

    public String retornoConsulta(String consulta, String url, int anoNascimento, int anoFalecimento, String tipoBusca) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new URL(url));
        Set<String> autoresExibidos = new HashSet<>();

        if (rootNode.has("results") && rootNode.get("results").isArray() && !rootNode.get("results").isEmpty()) {
            for (JsonNode livroNode : rootNode.get("results")) {
                for (JsonNode autorNode : livroNode.get("authors")) {
                    String nome = autorNode.get("name").asText();
                    int birthYear = autorNode.has("birth_year") && !autorNode.get("birth_year").isNull() ? autorNode.get("birth_year").asInt() : 0;
                    int deathYear = autorNode.has("death_year") && !autorNode.get("death_year").isNull() ? autorNode.get("death_year").asInt() : 0;

                    if (!autoresExibidos.contains(nome) &&
                            (tipoBusca.equals("nome") ||
                                    (tipoBusca.equals("nascimento") && birthYear == anoNascimento) ||
                                    (tipoBusca.equals("falecimento") && deathYear == anoFalecimento))) {

                        Autor autor = new Autor();
                        autor.setNome(nome);
                        autor.setAnoNascimento(birthYear);
                        autor.setAnoFalecimento(deathYear);

                        System.out.println("Nome: " + autor.getNome());
                        System.out.println("Ano de Nascimento: " + (autor.getAnoNascimento() != 0 ? autor.getAnoNascimento() : "Desconhecido"));
                        System.out.println("Ano de Falecimento: " + (autor.getAnoFalecimento() != 0 ? autor.getAnoFalecimento() : "Desconhecido"));
                        System.out.println();

                        autoresExibidos.add(nome);
                    }
                }
            }
        } else {
            System.out.println("Nenhum autor encontrado para a consulta: " + consulta);
        }
        return consulta;
    }
}
