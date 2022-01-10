package com.company.GameNavalBatter;





import static com.company.GameNavalBatter.styleGame.letsGameInicio;
import static com.company.GameNavalBatter.settings.*;
public class runGame {


    public static void main(String[] args) {
        letsGameInicio();
        NomesDosJogadores();
        tamanhoDoTabuleiro();

        instanciarQuantidadeDeNavios();
        inserirOsNaviosNosTabuleirosDosJogadores();
        tabuleiroDecição();
        exibirTabuleirosDosJogadores();

    }

}