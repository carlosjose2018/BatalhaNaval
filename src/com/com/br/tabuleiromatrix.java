package com.com.br;

import java.util.Random;
import java.util.Scanner;

public class tabuleiromatrix {

    static final int VAZIO = 0;
    static final int NAVIO = 1;
    static final int ERROU_TIRO = 2;
    static final int ACERTOU_TIRO = 3;
    static final int TIRO_CERTEIRO = 4;
    static final int TIRO_NA_AGUA_COM_NAVIO =5;
    static final int LETRA = 0;
    static final int NUMERO = 1;
    static final int USUARIO = 1;
    static final int COMPUTADOR = 2;

    static String capitao, pirata;
    static int tamanhoX =9, tamanhoY=9, quantidadeDeNavios=10;
    static int tabuleiroCapitao[][], tabuleiroPirata[][];
    static Scanner input = new Scanner(System.in);
    static int naviosCapitao, naviosPirata;

    public static void NomesDosJogadores() {
        Random random = new Random();
        int numero = random.nextInt(3);
        System.out.println("Digite o nome do Capitão: ");
        capitao = "Capitão  " + input.next();

        String[] nomes = new String[]{
                "Capitão Barba Branca",
                "Capitão Jack Sparrow",
                "Capitão Alma Negra",
                "Capitão  Gancho"
        };

        pirata = nomes[numero];
    }

    public static void tamanhoDoTabuleiro() {
        tabuleiroCapitao = tabuleiroVazio();
        tabuleiroPirata = tabuleiroVazio();
    }

    public static int[][] tabuleiroVazio() {
        return new int[tamanhoX][tamanhoY]; // Inicializar com 0 para tirar lixo de memória
    }

    public static void instanciarQuantidadeDeNavios() { // Desnecessário
        naviosCapitao = quantidadeDeNavios;
        naviosPirata = quantidadeDeNavios;
    }

    public static int[][] novosTabuleiroComOsNavios() {
        int novoTabuleiro[][] = tabuleiroVazio();
        int quantidadeRestanteDeNavios = quantidadeDeNavios;
        int x, y;
        Random rand = new Random();

        /*
            1 - Chutar um lugar aleatório
            2 - Se nao tiver barco, coloca
                Se tiver, volte pra opção 1
            3 - Enquanto tiver barco pra colocar
        */
        while(quantidadeRestanteDeNavios > 0){
            x = rand.nextInt(tamanhoX);
            y = rand.nextInt(tamanhoY);

            if(novoTabuleiro[x][y] == VAZIO){
                novoTabuleiro[x][y] = NAVIO;
                quantidadeRestanteDeNavios--;
            }
        }

        return novoTabuleiro;
    }

    /* ideia para o to-do (insercao manual)
        1 - Ler as N posições
            - Só vai ler a proxima posição quando a atual for válida (usar receberValorDigitadoPeloJogador)
        2 - Depois de ter as N posições, criar um tabuleiro vazio e colocar os N navios nas N posições recebidas (usar retornarPosicoesDigitadasPeloJogador para quebrar a posição lida em Letra e número)
    */

    public static void inserirOsNaviosNosTabuleirosDosJogadores() {
        tabuleiroCapitao = novosTabuleiroComOsNavios(); //TODO: Criar função que de do usuário todas as pocições que ele quer navio
        tabuleiroPirata = novosTabuleiroComOsNavios();
    }

    public static void numeroDoTabuleiroY() {
        for(int i = 1; i <= tamanhoY; i++){
            System.out.print("   " + i);
        }
        System.out.print("\n");
    }

    public static void exibirTabuleiro(String nomeDoJogador, int[][] tabuleiro, boolean seuTabuleiro) {
        System.out.println("|----- " + nomeDoJogador + " -----|");
        numeroDoTabuleiroY();

        char letraDaLinha = 'A';
        String linhaDoTabuleiro;
        char[] simbolos = new char[]{' ', 'N', '-', '*', 'X', 'n'};

        for(int x = 0; x < tamanhoX; x++) {
            linhaDoTabuleiro = (letraDaLinha++) + " | ";
            for(int y = 0; y < tamanhoY; y++){
                int simbolo = simbolos[tabuleiro[x][y]];

                // Esconde o navio do inimigo
                if(!seuTabuleiro && simbolo == 'N') {
                    simbolo = simbolos[VAZIO];
                }

                linhaDoTabuleiro += (char)(simbolo) + " | ";
            }
            System.out.println(linhaDoTabuleiro);
        }
    }

    public static void exibirTabuleirosDosJogadores() {
        //caso seja retirado o tabuliro em baixo muda.
        //Posivel implementação de vez ex  vez do pirata;
        exibirTabuleiro(capitao, tabuleiroCapitao, true);
        exibirTabuleiro(pirata, tabuleiroPirata, false);
    }

    public static boolean validarPosicoesInseridasPeloJogador(String tiroDoJogador) {
        int[] posicoes = retornarPosicoesDigitadasPeloJogador(tiroDoJogador);
        int letra = posicoes[0], numero = posicoes[1];

        if (letra > tamanhoX || letra <= 0) {
            System.out.println("A posicao da letra deve estar no intervalo de A até " + (char)(tamanhoX + 64));
            return false;
        }

        if (numero > tamanhoY || numero <= 0) {
            System.out.println("A posicao numérica deve estar no intervalo de 1 até " + tamanhoY);
            return false;
        }

        return true;
    }

    public static String receberValorDigitadoPeloJogador() {
        System.out.println("Digite a posição que deseja efetuar a ação:");
        String tiroDoJogador = input.next();

        if (validarTiroDoJogador(tiroDoJogador) == false) { // Análise sintática
            System.out.println("Posição inválida");
            return receberValorDigitadoPeloJogador();
        }

        if (validarPosicoesInseridasPeloJogador(tiroDoJogador) == false) { // Análise semântica
            System.out.println("Posição inválida");
            return receberValorDigitadoPeloJogador();
        }

        return tiroDoJogador;
    }

    public static boolean validarTiroDoJogador(String tiroDoJogador) {
        String expressaoDeVerificacao = "^[A-Za-z]{1}[0-9]{1}$";
        return tiroDoJogador.matches(expressaoDeVerificacao);
    }

    public static int[] retornarPosicoesDigitadasPeloJogador(String tiroDoJogador) {
        int[] posicoes = new int[2];
        tiroDoJogador = tiroDoJogador.toLowerCase();
        posicoes[LETRA] = tiroDoJogador.charAt(0) - 'a' + 1;
        posicoes[NUMERO] = tiroDoJogador.charAt(1) - '0';
        return posicoes;
    }

    public static void inserirValoresDaAcaoNoTabuleiro(int letra, int numero, int numeroDoJogador) {
        letra--;
        numero--;
        if(numeroDoJogador == USUARIO){
            if (tabuleiroPirata[letra][numero] == NAVIO){ // Acertei um navio
                naviosPirata--;
                if(tabuleiroCapitao[letra][numero] == NAVIO) {
                    tabuleiroPirata[letra][numero] = TIRO_CERTEIRO;
                    tabuleiroCapitao[letra][numero] = TIRO_CERTEIRO;
                    System.out.println("Que tiro certeiro! Ainda faltam " + naviosPirata + " navio(s)");
                } else {
                    tabuleiroPirata[letra][numero]= ACERTOU_TIRO;
                    System.out.println("Acertamos um navio pirata! Ainda faltam " + naviosPirata + " navio(s)");
                }
            } else if(tabuleiroPirata[letra][numero] == VAZIO) { // Errei um navio
                if(tabuleiroCapitao[letra][numero] == NAVIO) {
                    tabuleiroPirata[letra][numero] = TIRO_NA_AGUA_COM_NAVIO;
                    tabuleiroCapitao[letra][numero] = TIRO_NA_AGUA_COM_NAVIO;
                    System.out.println("Capitão Danificamos nosso navio e ainda erramoso tiro");
                } else {
                    tabuleiroPirata[letra][numero] = ERROU_TIRO;
                    System.out.println("Erramos o tiro capitão");
                }
            } else { // Já fiz essa jogada
                System.out.println("Opa, já fizemos essa jogada!");
            }
        } else {
            if (tabuleiroCapitao[letra][numero] == NAVIO){ // Computador acertou um navio
                tabuleiroCapitao[letra][numero] = ACERTOU_TIRO;
                naviosCapitao--;
                System.out.println("Nossa! Os piratas acertaram um navio nosso, ainda temos " + naviosCapitao + " navio(s)");
            } else if(tabuleiroCapitao[letra][numero] == VAZIO){ // Computador errou um navio
                tabuleiroCapitao[letra][numero] = ERROU_TIRO;
                System.out.println("O computador errou o tiro!");
            } else { // Computador já fez essa jogada
                System.out.println("Opa, o computador já fez essa jogada!");
            }
        }
    }

    public static void acaoDoJogador() {
        String tiroDoJogador = receberValorDigitadoPeloJogador();
        int[] posicoes = retornarPosicoesDigitadasPeloJogador(tiroDoJogador);
        int letra = posicoes[0], numero = posicoes[1];

        inserirValoresDaAcaoNoTabuleiro(letra, numero, USUARIO);
    }

    public static void acaoDoComputador() {
        int[] posicoes = retornarJogadaDoComputador();
        int letra = posicoes[0], numero = posicoes[1];
        inserirValoresDaAcaoNoTabuleiro(letra, numero, COMPUTADOR);
    }

    public static int[] retornarJogadaDoComputador() {
        int[] posicoes = new int[2];
        posicoes[LETRA] = retornarJogadaAleatoriaDoComputador(tamanhoX) + 1; // +1 para indexar em 1 igual a jogada do usuário pois o rand vai de 0 até n - 1
        posicoes[NUMERO] = retornarJogadaAleatoriaDoComputador(tamanhoY) + 1;
        return posicoes;
    }

    public static int retornarJogadaAleatoriaDoComputador(int limite) {
        Random jogadaDoComputador = new Random();
        int numeroGerado = jogadaDoComputador.nextInt(limite);
        return numeroGerado;
    }

    public static  void tabuleiroDecição(){
        while (true) {
            exibirTabuleirosDosJogadores();
            acaoDoJogador();
            if (naviosPirata <= 0) {
                System.out.println(capitao + " venceu o jogo!");
                break;
            }
            acaoDoComputador();
            if (naviosCapitao <= 0) {
                System.out.println(pirata + " venceu o jogo!");
                break;
            }
        }
    }
}
