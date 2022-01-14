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

• Durante a partida o programa exibirá apenas a situação atual do jogador.

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
