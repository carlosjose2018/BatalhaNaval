package java1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Aulas_Java {

    static int tamanhoX, tamanhoY, quantidadeDeNavios, limiteMaximoDeNavios;
    static int tabuleiroJogador1[][], tabuleiroJogador2[][];
    static Scanner input = new Scanner(System.in);

    public static void obterTamanhoDosTabuleiros() {

        for (;;){
            boolean tudoOk = false;
            try {
                System.out.println("Digite a altura do tabuleiro: ");
                tamanhoX = input.nextInt();
                System.out.println("Digite o comprimento do tabuleiro: ");
                tamanhoY = input.nextInt();
                tudoOk = true;
            } catch (InputMismatchException erro) {
                System.out.println("Digite um valor numérico");
            }
            if(tudoOk) {
                break;
            }
        }
    }

    public static void calcularQuantidadeMaximaDeNaviosNoJogo() {
        limiteMaximoDeNavios = (tamanhoX * tamanhoY) / 3;
    }

    public static void iniciandoOsTamanhosDosTabuleiros() {
        tabuleiroJogador1 = retornarNovoTabuleiroVazio();
        tabuleiroJogador2 = retornarNovoTabuleiroVazio();
    }

    public static int[][] retornarNovoTabuleiroVazio() {
        return new int[tamanhoX][tamanhoY];
    }

    public static void obterQuantidadeDeNaviosNoJogo() {
        System.out.println("Digite a quantidade de navios do jogo:");
        System.out.println("Max: " + limiteMaximoDeNavios + " navios");
        quantidadeDeNavios = input.nextInt();
        if (quantidadeDeNavios < 1 && quantidadeDeNavios > limiteMaximoDeNavios) {
            quantidadeDeNavios = limiteMaximoDeNavios;
        }
    }

    public static int[][] retornarNovoTabuleiroComOsNavios() {
        int novoTabuleiro[][] = retornarNovoTabuleiroVazio();
        int quantidadeRestanteDeNavios = quantidadeDeNavios;
        Random numeroAleatorio = new Random();
        do {
            for(int[] linha : novoTabuleiro) {
                for (int coluna : linha) {
                    if (numeroAleatorio.nextInt(100) <= 10) {
                        if(coluna == 0) {
                            coluna = 1;
                            quantidadeRestanteDeNavios--;
                            break;
                        }
                        if(quantidadeRestanteDeNavios <= 0) {
                            break;
                        }
                    }
                }
                if(quantidadeRestanteDeNavios <= 0) {
                    break;
                }
            }
        } while (quantidadeRestanteDeNavios > 0);
        return novoTabuleiro;
    }

    public static void inserirOsNaviosNosTabuleirosDosJogadores() {
        tabuleiroJogador1 = retornarNovoTabuleiroComOsNavios();
        tabuleiroJogador2 = retornarNovoTabuleiroComOsNavios();
    }

    public static void main(String[] args) {

        obterTamanhoDosTabuleiros();
        calcularQuantidadeMaximaDeNaviosNoJogo();
        iniciandoOsTamanhosDosTabuleiros();
        obterQuantidadeDeNaviosNoJogo();
        inserirOsNaviosNosTabuleirosDosJogadores();

        input.close();

    }
}