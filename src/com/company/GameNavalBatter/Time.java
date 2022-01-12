package com.company.GameNavalBatter;

import java.util.TimerTask;
import java.util.Timer;



public class Time {

    public static void tempoDeInicalizarMetodos( String paramant){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 1000L);
    }

}
