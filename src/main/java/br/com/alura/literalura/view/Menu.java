package br.com.alura.literalura.view;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void exibirMenu() throws IOException {
        while(true) {
            printMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    SubMenuLivro subMenuLivro = new SubMenuLivro(scanner);
                    subMenuLivro.selecionarTipoBuscaLivro();
                case 2:
                    SubMenuAutor subMenuAutor = new SubMenuAutor(scanner);
                    subMenuAutor.selecionarTipoBuscaAutor();
                case 3:
                    System.out.println("em andamento3");
                    break;
                case 4:
                    System.out.println("em andamento4");
                    break;
                case 5:
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
        System.out.println("1. Buscar Livros");
        System.out.println("2. Buscar Autores");
        System.out.println("3. Minha Coleção");
        System.out.println("4. Estatísticas");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
