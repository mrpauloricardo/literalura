package br.com.alura.literalura.view;

import java.io.IOException;
import java.util.Scanner;

public class SubMenuLivro {
    private final Scanner scanner;

    public SubMenuLivro(Scanner scanner) {
        this.scanner = scanner;
    }

    public String selecionarTipoBuscaLivro() throws IOException {
        while (true) {
            printSubMenuLivro();
            int opcaoBusca = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoBusca) {
                case 1: return new BuscaLivro().buscaPorTitulo(scanner);
                case 2: return new BuscaLivro().buscaPorAutor(scanner);
                case 3: return new BuscaLivro().buscaPorIdioma(scanner);
                case 4: return null;
                case 5:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                    return null;
            }
        }
    }

    private void printSubMenuLivro() {
        System.out.println("\n--- Selecione o tipo de busca a ser realizada: ---");
        System.out.println("1. Buscar Livros por Título");
        System.out.println("2. Buscar Livros por Autor");
        System.out.println("3. Buscar Livros por Idioma (Digite apenas as iniciais do idioma (Exemplo: pt)");
        System.out.println("4. Menu anterior");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
