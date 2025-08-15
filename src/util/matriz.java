package util;

import java.util.ArrayList;
import java.util.Scanner;

public class matriz {
    ArrayList<Integer> lista = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int linha;
    int coluna;
    int[][] matriz;

    public void imprimirMatriz() {
        System.out.println("Matriz");
        System.out.println("_____________________");
        System.out.print("Entre com a quantidade de linhas: ");
        linha = sc.nextInt();
        System.out.print("Entre com a quantidade de colunas: ");
        coluna = sc.nextInt();

        System.out.print("É uma matriz vazia? sim(s)|não(n): ");
        char resposta = sc.next().toLowerCase().charAt(0);

        if (resposta == 's') {
            matriz = defaultMatriz(linha, coluna);
            lacoPadrao(matriz, linha, coluna);
        } else if (resposta == 'n') {
            entradaDados();
            matriz = construirMatriz(lista, linha, coluna);
            lacoPadrao(matriz, linha, coluna);
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public static int[][] construirMatriz(ArrayList<Integer> array, int linha, int coluna) {
        int[][] matriz = new int[linha][coluna];
        int k = 0;
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                matriz[i][j] = array.get(k++);
            }
        }
        return matriz;
    }

    public void imprimirMarcadores(int coluna) {
        for (int i = 0; i < coluna * 2; i++) {
            System.out.print("_");
        }
    }

    public int[][] defaultMatriz(int linha, int coluna) {
        return new int[linha][coluna];

    }

    public void lacoPadrao(int[][] matriz, int linha, int coluna) {
        imprimirMarcadores(coluna);
        System.out.println();
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        imprimirMarcadores(coluna);
        System.out.println();
    }

    public void entradaDados() {
        int totalElementos = linha * coluna;
        lista.clear();
        for (int i = 0; i < totalElementos; i++) {
            System.out.print("Digite o elemento " + (i + 1) + ": ");
            lista.add(sc.nextInt());
        }
    }
}
