package com.example.gastos;

import android.telecom.Call;

public class SimuladorModular {

    //objeto
    public static class Solicitud {
        public int dividendo;
        public int divisor;

        public Solicitud(int dividendo, int divisor) {
            this.dividendo = dividendo;
            this.divisor = divisor;
        }
    }

    //CallBack
    interface Callback {
        void cuandoEsteCalculadoElResultado(int resultado);
        void cuandoElDivisorSeaIncorrecto();
    }

    //calcula si es modular o no
    public void calcular(Solicitud solicitud, Callback callback) {
        try {
            Thread.sleep(4000);   // simular operacion de larga duracion (4s)
        } catch (InterruptedException e) {}

        if(solicitud.divisor < 0){
            callback.cuandoElDivisorSeaIncorrecto();
        }else{


            int i = solicitud.dividendo%solicitud.divisor;
            //if (i==0){
            //    return 0;
            //}else return -1;
            if (i==0) {
                callback.cuandoEsteCalculadoElResultado(0);
            } else {
                callback.cuandoEsteCalculadoElResultado(-1);
            }
        }

    }
}
