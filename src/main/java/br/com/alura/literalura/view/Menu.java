package br.com.alura.literalura.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class Menu {
    private final Scanner scanner;
    private final BuscaLivro buscaLivro;
    private final BuscaAutor buscaAutor;

    @Autowired
    public Menu(Scanner scanner, BuscaLivro buscaLivro, BuscaAutor buscaAutor) {
        this.scanner = scanner;
        this.buscaLivro = buscaLivro;
        this.buscaAutor = buscaAutor;
    }

    public void exibirMenu() throws IOException {
        while (true) {
            printMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscaLivro.buscaPorTitulo(scanner);
                    break;
                case 2:
                    buscaLivro.buscaPorIdioma(scanner);
                    break;
                case 3:
                    buscaLivro.buscaTodos();
                    break;
                case 4:
                    buscaAutor.buscaTodosAutores();
                    break;
                case 5:
                    buscaAutor.buscaAutoresVivosEmAno(scanner);
                    break;
                case 6:
                    buscaLivro.buscaMaisBaixados();
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public void printMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Buscar Livro por Título");
        System.out.println("2. Buscar Livros por Idioma");
        System.out.println("3. Buscar Todos os Livros");
        System.out.println("4. Buscar Autores");
        System.out.println("5. Buscar Autores vivos em um determinado ano");
        System.out.println("6. Top 10 Livros mais baixados");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }
}