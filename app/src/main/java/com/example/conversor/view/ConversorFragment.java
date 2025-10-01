package com.example.conversor.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.conversor.R;
import com.example.conversor.viewmodel.ConversorViewModel;

public class ConversorFragment extends Fragment {

    private EditText editDolar, editEuro, editResultado;
    private RadioButton radioDolarAEuro, radioEuroADolar;
    private Button btnConvertir;
    private ConversorViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editDolar = view.findViewById(R.id.editTextDolar);
        editEuro = view.findViewById(R.id.editTextEuro);
        editResultado = view.findViewById(R.id.editTextResultado);
        radioDolarAEuro = view.findViewById(R.id.radioDolarAEuro);
        radioEuroADolar = view.findViewById(R.id.radioEuroADolar);
        btnConvertir = view.findViewById(R.id.btnConvertir);

        viewModel = new ViewModelProvider(requireActivity()).get(ConversorViewModel.class);

        // Observadores LiveData
        viewModel.getResultado().observe(getViewLifecycleOwner(), res -> editResultado.setText(res));
        viewModel.getMensaje().observe(getViewLifecycleOwner(), msg -> Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show());

        // RadioButtons y habilitación de EditText
        viewModel.getRadioDolarAEuroSeleccionado().observe(getViewLifecycleOwner(), selected -> {
            radioDolarAEuro.setChecked(selected != null && selected);
            editDolar.setEnabled(selected != null && selected);
        });

        viewModel.getRadioEuroADolarSeleccionado().observe(getViewLifecycleOwner(), selected -> {
            radioEuroADolar.setChecked(selected != null && selected);
            editEuro.setEnabled(selected != null && selected);
        });

        // Clicks -> notifican al ViewModel
        radioDolarAEuro.setOnClickListener(v -> viewModel.seleccionarDolarAEuro());
        radioEuroADolar.setOnClickListener(v -> viewModel.seleccionarEuroADolar());

        // Botón convertir
        btnConvertir.setOnClickListener(v -> {
            viewModel.setValorDolar(editDolar.getText().toString());
            viewModel.setValorEuro(editEuro.getText().toString());
            viewModel.convertir();
        });

        // Inicializar estado
        editDolar.setEnabled(false);
        editEuro.setEnabled(false);
    }
}
