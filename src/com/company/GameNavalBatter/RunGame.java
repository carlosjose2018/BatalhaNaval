package com.company.GameNavalBatter;


import java.util.Timer;
import java.util.TimerTask;


import static com.company.GameNavalBatter.Settings.*;


import static com.company.GameNavalBatter.Stylegame.letsGameInicio;

public class RunGame {

    public static void main(String[] args) {
        letsGameInicio();


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                NomesDosJogadores();
                tamanhoDoTabuleiro();
                instanciarQuantidadeDeNavios();
                inserirOsNaviosNosTabuleirosDosJogadores();
                tabuleiroDecisão();
                exibirTabuleirosDosJogadores();
            }
        }, 1000L);

    }

}