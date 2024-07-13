package br.com.alura.literalura;

import br.com.alura.literalura.view.Menu;

import java.io.IOException;
import java.util.Scanner;

public class LiteraluraApplication {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        menu.exibirMenu();
    }
}
