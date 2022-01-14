#                                       Batalha Naval

Batalha naval foi desenvolvida em java .

## Definiçao

Abaixo segue a definição do jogo “Batalha Naval” retirada do site Wikipedia:
“Batalha naval é um jogo de tabuleiro de dois jogadores, no qual os jogadores
têm de adivinhar em que quadrados estão os navios do oponente. Embora
tenha sido o primeiro jogo em tabuleiro comercializado e publicado pela Milton
Bradley Company em 1931, o jogo foi originalmente jogado com lápis e papel.
O jogo original é jogado em duas grelhas para cada jogador - uma que
representa a disposição dos barcos do jogador, e outra que representa a do
oponente. As grelhas são tipicamente quadradas, estando identificadas na
horizontal por números e na vertical por letras. Em cada grelha o jogador
coloca os seus navios e registra os tiros do oponente.
Antes do início do jogo, cada jogador coloca os seus navios nos quadros,
alinhados horizontalmente ou verticalmente. O número de navios permitidos é
igual para ambos jogadores e os navios não podem se sobrepor.
Após os navios terem sido posicionados o jogo continua numa série de turnos,
em cada turno um jogador diz um quadrado na grelha do oponente, se houver
um navio nesse quadrado, é colocada uma marca vermelha, senão houver é
colocada uma marca branca.
Os tipos de navios são: porta-aviões (5 quadrados adjacentes em forma de T),
os submarinos (1 quadrado apenas), barcos de dois, três e quatro canos.
Numa das variações deste jogo, as grelhas são de dimensão 10x10, e o
número de navios são: 1, 4, 3, 2, 1, respectivamente.”

# Desafio
Agora que todos conhecemos a definição do jogo, o desafio consiste em
implementar esse jogo em um programa Java, mas para facilitar um pouco,
vamos alterar levemente as especificações do jogo:

• Em nossa versão, teremos apenas navios do tipo submarino (1 quadrado).

• Cada jogador deverá posicionar em sua grelha dez submarinos.

• O programa aceitará apenas um jogador, pois o oponente será o computador.

• Para não precisarmos utilizar cores no terminal vamos usar as marcações:

. Navio posicionado N (ene maiúsculo)

. Tiro certeiro * (asterisco)

. Tiro na água – (traço)

. Tiro certeiro com navio posicionado X (xis maiúsculo)

. Tiro na água com navio posicionado n (ene minúsculo)

Desafio: Batalha Naval

• Durante a partida o programa exibirá apenas a situação atual do jogador este tabuleiro pode ser gerado manual ou automatico.

• Em cada turno, a situação atual do tabuleiro do jogador deverá ser
impressa na tela de acordo com o modelo a seguir:

```
---------------------------------------------
                   JOGADOR
---------------------------------------------
|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
---------------------------------------------
| A | N |   |   |   |   |   |   |   |   |   |
---------------------------------------------
| B |   |   |   | - |   | * |   |   |   | N |
---------------------------------------------
| C |   |   | N |   |   |   |   |   | - |   |
---------------------------------------------
| D |   |   |   |   | n |   |   |   |   |   |
---------------------------------------------
| E |   |   |   |   |   |   | N |   |   |   |
---------------------------------------------
| F |   | X |   |   |   |   |   |   |   |   |
---------------------------------------------
| G |   |   |   |   |   |   |   |   | N |   |
---------------------------------------------
| H |   |   |   |   |   |   | N |   |   |   |
---------------------------------------------
| I | N |   | - |   |   |   |   |   |   |   |
---------------------------------------------
| J |   |   |   |   |   |   | N |   |   |   |
---------------------------------------------
````
# Codificando

```
                      inserirOsNaviosNosTabuleirosDosJogadores();
```
````
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
    
````

// Método para inserir Navio no tabuleiro dependendo da escolha do usuário podendo ser tanto automático como manual.

#
```
                                  ExibirTabuleiro();
```
````
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
    
````
/*
/* Método exibirTabuleiro tem 3 parâmetro cujo parâmetro faz inserção de nome, cria string linhas e colunas e posiciona   caracteres  tabuleiro. 
*/

#
```
                                 inserirValoresDaAcaoNoTabuleiro();
```
````
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
    
````
/*
Método inserirValoresDaAcaoNoTabuleiro  tem a logica principal do jogo através dela é verificado a posição do navio ,tiro certeiro ,tiro na agua ,Tiro certeiro com navio posicionado X (xis maiúsculo) e Tiro na água com navio posicionado n (ene minúsculo).

*/

## License
[MIT](https://choosealicense.com/licenses/mit/)