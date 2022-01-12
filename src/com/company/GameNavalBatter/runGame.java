package com.company.GameNavalBatter;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.company.GameNavalBatter.Settings.*;
import static com.company.GameNavalBatter.Stylegame.anime;
import static com.company.GameNavalBatter.Stylegame.letsGameInicio;

public class runGame {

    public static void main(String[] args) {
        letsGameInicio();


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                anime();
                NomesDosJogadores();
                tamanhoDoTabuleiro();
                instanciarQuantidadeDeNavios();
                inserirOsNaviosNosTabuleirosDosJogadores();
                tabuleiroDecição();
                exibirTabuleirosDosJogadores();
            }
        }, 1000L);

    }

}