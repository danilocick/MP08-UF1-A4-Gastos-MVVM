package com.example.gastos;

import android.app.Application;
import android.telecom.Call;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MiModularViewModel extends AndroidViewModel {

    Executor executor;

    SimuladorModular simulador;

    MutableLiveData<Integer> mLiveData = new MutableLiveData<>();
    MutableLiveData<String> mLiveData2 = new MutableLiveData<>();

    public MiModularViewModel(@NonNull Application application) {
        super(application);

        executor = Executors.newSingleThreadExecutor();
        simulador = new SimuladorModular();
    }

    public void calcular(int dividendo, int divisor) {

        final SimuladorModular.Solicitud solicitud = new SimuladorModular.Solicitud(dividendo, divisor);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                simulador.calcular(solicitud, new SimuladorModular.Callback() {
                    @Override
                    public void cuandoEsteCalculadoElResultado(int resultado) {
                        mLiveData.postValue(resultado);
                    }

                    @Override
                    public void cuandoElDivisorSeaIncorrecto() {
                        mLiveData2.postValue("Error:Introducte un número");
                    }
                });
            }
        });
    }

}
