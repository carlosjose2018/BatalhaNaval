package com.company.GameNavalBatter;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.function.DoubleFunction;

import static com.company.GameNavalBatter.Stylegame.*;

public class Settings {

    static final int VAZIO = 0;
    static final int NAVIO = 1;
    static final int ERROU_TIRO = 2;
    static final int ACERTOU_TIRO = 3;
    static final int TIRO_CERTEIRO = 4;
    static final int TIRO_NA_AGUA_COM_NAVIO = 5;

    static final int LETRA = 0;
    static final int NUMERO = 1;
    static final int USUARIO = 1;
    static final int COMPUTADOR = 2;


    static String capitao, pirata;
    static int tamanhoX = 10, tamanhoY = 10, quantidadeDeNavios = 10;
    static int tabuleiroCapitao[][], tabuleiroPirata[][];
    static Scanner input = new Scanner(System.in);
    static int naviosCapitao, naviosPiratas;

    public static void nomesDosJogadores() {
        Random random = new Random();
        int numero = random.nextInt(3);
        System.out.println("Qual o seu nome Marinheiro: ");
        capitao = input.next();

        String[] nomes = new String[]{
                "Capitão Barba Branca",
                "Capitão Jack Sparrow",
                "Capitão Alma Negra",
                "Capitão Gancho"
        };

        pirata = nomes[numero];
    }

    public static void tamanhoDoTabuleiro() {
        tabuleiroCapitao = tabuleiroVazio();
        tabuleiroPirata = tabuleiroVazio();
    }

    public static int[][] tabuleiroVazio() {
        return new int[tamanhoX][tamanhoY];
    }


    public static void instanciarQuantidadeDeNavios() {
        naviosCapitao = quantidadeDeNavios;
        naviosPiratas = quantidadeDeNavios;
    }

    public static int[][] novosTabuleiroComOsNaviosManual() {
        int novoTabuleiro[][] = tabuleiroVazio();
        int quantidadeRestanteDeNavios = 10;
        int quantidadeTotalDeNavios = 0;
        int x = 0, y = 0;
        do {
            x = 0;
            y = 0;
            Scanner input = new Scanner(System.in);
            for (int[] linha : novoTabuleiro) {
                for (int coluna : linha) {
                    if (coluna == VAZIO) {
                        try {
                            tabela();
                            System.out.println("Posicione o navio no tabuleiro :\n Quantidade de navio Posicionado :" + quantidadeTotalDeNavios);
                            String linhaTabela = input.next().toLowerCase();
                            x = linhaTabela.charAt(0) - 97;
                            y = Integer.parseInt(linhaTabela.substring(1)) - 1;
                        } catch (ArrayIndexOutOfBoundsException erro) {
                            System.out.println("Para posicionar o navio use o padrão letras para linhas e número para colunas:");
                            System.out.println("Posicione o navio no tabuleiro :\n Quantidade de navio " + quantidadeTotalDeNavios);
                            String linhaTabela = input.next().toLowerCase();
                            x = linhaTabela.charAt(0) - 97;
                            y = Integer.parseInt(linhaTabela.substring(1)) - 1;
                        }
                        // printar aqui....
                        novoTabuleiro[x][y] = NAVIO;
                        quantidadeRestanteDeNavios--;
                        quantidadeTotalDeNavios++;
                        break;
                    }
                    if (quantidadeRestanteDeNavios < 0) {
                        break;

                    }
                    y++;
                }
                y = 0;
                x++;
                if (quantidadeRestanteDeNavios <= 0) {
                    break;
                }
            }
        } while (quantidadeRestanteDeNavios > 0);
        return novoTabuleiro;
    }


    public static int[][] novosTabuleiroComOsNavios() {
        int novoTabuleiro[][] = tabuleiroVazio();
        int quantidadeRestanteDeNavios = quantidadeDeNavios;
        int x, y;
        Random rand = new Random();
        while (quantidadeRestanteDeNavios > 0) {
            x = rand.nextInt(tamanhoX);
            y = rand.nextInt(tamanhoY);

            if (novoTabuleiro[x][y] == VAZIO) {
                novoTabuleiro[x][y] = NAVIO;
                quantidadeRestanteDeNavios--;
            }
        }
        return novoTabuleiro;
    }


    public static void inserirOsNaviosNosTabuleirosDosJogadores() {
        Scanner input = new Scanner(System.in);
        System.out.println("Gostaria de posicionar os navios manualmente ou automático? Digite M para manual ou A para automático");
        String definirModoPosicao = input.next().toLowerCase();
        System.out.println(definirModoPosicao);
        if (definirModoPosicao.equals("a")){
            tabuleiroCapitao = novosTabuleiroComOsNavios();
            tabuleiroPirata = novosTabuleiroComOsNavios();
        }
        else {
            tabuleiroCapitao = novosTabuleiroComOsNaviosManual();
            tabuleiroPirata = novosTabuleiroComOsNavios();
        }
    }


    public static void numeroDoTabuleiroX() {
        for (int i = 1; i <= tamanhoY; i++) {
            System.out.print("   " + i);
        }
        System.out.print("\n");
    }

    public static void exibirTabuleiro(String nomeDoJogador, int[][] tabuleiro, boolean seuTabuleiro) {
        System.out.println("|----- " + nomeDoJogador + " -----|");
        numeroDoTabuleiroX();
        char letraDaLinha = 'A';
        String linhaDoTabuleiro;
        char[] simbolos = new char[]{' ', 'N', '-', '*', 'X', 'n'};

        for (int x = 0; x < tamanhoX; x++) {
            linhaDoTabuleiro = (letraDaLinha++) + " | ";
            for (int y = 0; y < tamanhoY; y++) {
                int simbolo = simbolos[tabuleiro[x][y]];

                // Esconde o navio do inimigo
                if (!seuTabuleiro && simbolo == 'N') {
                    simbolo = simbolos[VAZIO];
                }

                linhaDoTabuleiro += (char) (simbolo) + " | ";
            }
            System.out.println(linhaDoTabuleiro);
        }
    }

    public static void exibirTabuleirosDosJogadores() {
        exibirTabuleiro(capitao, tabuleiroCapitao, true);
        exibirTabuleiro(pirata,tabuleiroPirata,false);
    }

    public static boolean validarPosicoesInseridasPeloJogador(String tiroDoJogador) {
        int[] posicoes = retornarPosicoesDigitadasPeloJogador(tiroDoJogador);
        int letra = posicoes[0], numero = posicoes[1];

        if (letra > tamanhoX || letra <= 0) {
            System.out.println("A posicao da letra deve estar no intervalo de A até " + (char) (tamanhoX + 64));
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

        if (validarTiroDoJogador(tiroDoJogador) == false) {
            System.out.println("Posição inválida 1 ");
            return receberValorDigitadoPeloJogador();
        }

        if (validarPosicoesInseridasPeloJogador(tiroDoJogador) == false) {
            System.out.println("Posição inválida 2 ");
            return receberValorDigitadoPeloJogador();
        }

        return tiroDoJogador;
    }


    public static boolean validarTiroDoJogador(String tiroDoJogador) {
        //regex

        String expressaoDeVerificacaoDoisDigitos = "^[A-Za-z]{1}[0-9]{2}$";
        String expressaoDeVerificacaoUmDigito = "^[A-Za-z]{1}[0-9]{1}$";
        boolean doisDigitos = tiroDoJogador.matches(expressaoDeVerificacaoDoisDigitos);
        boolean umDigito = tiroDoJogador.matches(expressaoDeVerificacaoUmDigito);

        if (doisDigitos == true || umDigito == true) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] retornarPosicoesDigitadasPeloJogador(String tiroDoJogador) {
        int[] posicoes = new int[2];
        tiroDoJogador = tiroDoJogador.toLowerCase();
        posicoes[LETRA] = tiroDoJogador.charAt(0) - 'a' + 1;
        String posicoesNumericas = tiroDoJogador.substring(1);
        int resultado = Integer.parseInt(posicoesNumericas);
        posicoes[NUMERO] = resultado;

        return posicoes;
    }

    public static void inserirValoresDaAcaoNoTabuleiro(int letra, int numero, int numeroDoJogador) {
        letra--;
        numero--;

        if (numeroDoJogador == USUARIO) {
            if (tabuleiroPirata[letra][numero] == NAVIO) { // Acertei um navio
                naviosPiratas--;
                if (tabuleiroCapitao[letra][numero] == NAVIO) {
                    tabuleiroPirata[letra][numero] = TIRO_CERTEIRO;
                    tabuleiroCapitao[letra][numero] = TIRO_CERTEIRO;
                    if (naviosPiratas > 1){
                        System.out.printf("Que tiro certeiro! Ainda faltam %d navios a serem abatidos! \n",naviosPiratas);
                    }
                    else {
                        System.out.printf("Que tiro certeiro! Falta apenas %d navio para ganharmos a batalha! \n",naviosPiratas);
                    }
                }
                else {
                    tabuleiroPirata[letra][numero] = ACERTOU_TIRO;
//                    tabuleiroCapitao[letra][numero] = ACERTOU_TIRO;
                    System.out.println("Acertamos um navio pirata! Ainda faltam " + naviosPiratas + " navio(s)");
                }
            } else if (tabuleiroPirata[letra][numero] == VAZIO) { // Errei um navio
                if (tabuleiroCapitao[letra][numero] == NAVIO) {
                    tabuleiroPirata[letra][numero] = TIRO_NA_AGUA_COM_NAVIO;
                    tabuleiroCapitao[letra][numero] = TIRO_NA_AGUA_COM_NAVIO;
                    if (naviosPiratas > 1) {
                        System.out.printf("Capitão Danificamos nosso navio e ainda erramos tiro! Ainda faltam %d navios a serem abatidos! \n", naviosPiratas);
                    } else {
                        System.out.printf("Capitão Danificamos nosso navio e ainda erramos tiro! Falta apenas %d navio para ganharmos a batalha! \n", naviosPiratas);
                    }

                } else {
                    tabuleiroPirata[letra][numero] = ERROU_TIRO;
                    if (naviosPiratas > 1){
                        System.out.printf("Erramos o tiro capitão! Ainda faltam %d navios a serem abatidos! \n",naviosPiratas);
                    }
                    else {
                        System.out.printf("Erramos o tiro capitão! Mas falta apenas %d navio para ganharmos a batalha! \n",naviosPiratas);
                    }
                }
            } else { // Já fiz essa jogada
                System.out.println("Opa, já fizemos essa jogada!");
            }
        } else { // Jogada computador
            if (tabuleiroCapitao[letra][numero] == NAVIO || tabuleiroCapitao[letra][numero] == TIRO_NA_AGUA_COM_NAVIO) { // Computador acertou um navio
                tabuleiroCapitao[letra][numero] = ACERTOU_TIRO;
                naviosCapitao--;
                if (naviosCapitao > 1){
                    System.out.printf("Nossa! Os piratas acertaram um navio nosso! Ainda temos %d navios! \n",naviosCapitao);
                }
                else {
                    System.out.printf("Nossa! Os piratas acertaram um navio nosso! Resta apenas %d navio para eles ganharem!\n", naviosCapitao);

                }
            } else if (tabuleiroCapitao[letra][numero] == VAZIO) { // Computador errou um navio
                tabuleiroCapitao[letra][numero] = ERROU_TIRO;
                if (naviosCapitao > 1){
                    System.out.printf("O computador errou o tiro! Ainda temos %d navios! \n",naviosCapitao);
                }
                else {
                    System.out.printf("O computador errou o tiro! Resta apenas %d navio para eles ganharem! \n", naviosCapitao);

                }
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
        posicoes[LETRA] = retornarJogadaAleatoriaDoComputador(tamanhoX) + 1;
        posicoes[NUMERO] = retornarJogadaAleatoriaDoComputador(tamanhoY) + 1;
        return posicoes;
    }

    public static int retornarJogadaAleatoriaDoComputador(int limite) {
        Random jogadaDoComputador = new Random();
        int numeroGerado = jogadaDoComputador.nextInt(limite);
        return numeroGerado;
    }


    public static void tabuleiroDecisão() {
        while (true) {
            exibirTabuleirosDosJogadores();
            acaoDoJogador();
            if (naviosPiratas <= 0) {
                voceGanhou();
                break;
            }
            acaoDoComputador();
            if (naviosCapitao <= 0) {
                vocePerdeu();
                break;
            }
        }
    }
}
