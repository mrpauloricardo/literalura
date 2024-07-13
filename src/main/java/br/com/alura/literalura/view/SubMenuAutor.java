package br.com.alura.literalura.view;

import java.io.IOException;
import java.util.Scanner;

public class SubMenuAutor {
    private final Scanner scanner;

    public SubMenuAutor(Scanner scanner) {
        this.scanner = scanner;
    }

    public String selecionarTipoBuscaAutor() throws IOException {
        while(true) {
            printSubMenuAutor();
            int opcaoBusca = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoBusca) {
                case 1: return new BuscaAutor().buscaPorNome(scanner);
                case 2: return new BuscaAutor().buscaPorAnoNascimento(scanner);
                case 3: return new BuscaAutor().buscaPorAnoFalecimento(scanner);
                case 4: return new BuscaAutor().buscaPorIntervalo(scanner);
                case 5: return null;
                case 6:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                    return null;
            }
        }
    }

    private void printSubMenuAutor() {
        System.out.println("\n--- Selecione o tipo de busca a ser realizada: ---");
        System.out.println("1. Buscar por nome");
        System.out.println("2. Buscar por ano de nascimento");
        System.out.println("3. Buscar por ano de falecimento");
        System.out.println("4. Buscar por um intervalo de tempo");
        System.out.println("5. Menu anterior");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
