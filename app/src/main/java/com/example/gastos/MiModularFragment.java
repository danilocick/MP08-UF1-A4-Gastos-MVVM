package com.example.gastos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gastos.databinding.FragmentMiModularBinding;

public class MiModularFragment extends Fragment {
    private FragmentMiModularBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMiModularBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MiModularViewModel miModularViewModel = new ViewModelProvider(this).get(MiModularViewModel.class);

        binding.calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int dividendo = Integer.parseInt(binding.dividendo.getText().toString());
                int divisor = Integer.parseInt(binding.divisor.getText().toString());

                miModularViewModel.calcular(dividendo,divisor);
            }
        });

        miModularViewModel.mLiveData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer cuota) {
                if(cuota == 0) {
                    binding.resultado.setText("Es modular");
                }else{binding.resultado.setText("No es modular");}
            }
        });

    }
}