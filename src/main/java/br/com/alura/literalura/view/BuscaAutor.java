package br.com.alura.literalura.view;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class BuscaAutor {

    private final AutorRepository autorRepository;

    @Autowired
    public BuscaAutor(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void buscaTodosAutores() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado ainda.");
        } else {
            System.out.println("\nTodos os autores cadastrados:");
            for (Autor autor : autores) {
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Ano de Nascimento: " + (autor.getAnoNascimento() != 0 ? autor.getAnoNascimento() : "Desconhecido"));
                System.out.println("Ano de Falecimento: " + (autor.getAnoFalecimento() != 0 ? autor.getAnoFalecimento() : "Desconhecido"));
                System.out.println("----------------------");
            }
        }
    }

    public void buscaAutoresVivosEmAno(Scanner scanner) {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        List<Autor> autores = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimento(ano, ano, 0);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano " + ano + ".");
        } else {
            System.out.println("\nAutores vivos no ano " + ano + ":");
            for (Autor autor : autores) {
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Ano de Nascimento: " + autor.getAnoNascimento());
                System.out.println("Ano de Falecimento: " + (autor.getAnoFalecimento() != 0 ? autor.getAnoFalecimento() : "Vivo"));
                System.out.println("----------------------");
            }
        }
    }
}
