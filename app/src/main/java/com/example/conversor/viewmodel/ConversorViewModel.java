package com.example.conversor.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.conversor.model.Conversor;

public class ConversorViewModel extends ViewModel {

    private Conversor conversor = new Conversor();

    private MutableLiveData<String> resultado = new MutableLiveData<>();
    private MutableLiveData<String> mensaje = new MutableLiveData<>();
    private MutableLiveData<Boolean> radioDolarAEuroSeleccionado = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> radioEuroADolarSeleccionado = new MutableLiveData<>(false);

    // Getters LiveData
    public LiveData<String> getResultado() { return resultado; }
    public LiveData<String> getMensaje() { return mensaje; }
    public LiveData<Boolean> getRadioDolarAEuroSeleccionado() { return radioDolarAEuroSeleccionado; }
    public LiveData<Boolean> getRadioEuroADolarSeleccionado() { return radioEuroADolarSeleccionado; }

    // Entrada desde UI
    public void setValorDolar(String valor) { conversor.setValorDolar(valor); }
    public void setValorEuro(String valor) { conversor.setValorEuro(valor); }

    // Selección de conversión
    public void seleccionarDolarAEuro() {
        radioDolarAEuroSeleccionado.setValue(true);
        radioEuroADolarSeleccionado.setValue(false);
    }

    public void seleccionarEuroADolar() {
        radioEuroADolarSeleccionado.setValue(true);
        radioDolarAEuroSeleccionado.setValue(false);
    }

    // Conversión
    public void convertir() {
        if (radioDolarAEuroSeleccionado.getValue() != null && radioDolarAEuroSeleccionado.getValue()) {
            String res = conversor.convertirDolarAEuro();
            if (res.isEmpty()) {
                mensaje.setValue("Ingrese un valor en dólares");
            } else {
                resultado.setValue(res); // Ej: "1.00 USD = 0.92 EUR"
            }
        } else if (radioEuroADolarSeleccionado.getValue() != null && radioEuroADolarSeleccionado.getValue()) {
            String res = conversor.convertirEuroADolar();
            if (res.isEmpty()) {
                mensaje.setValue("Ingrese un valor en euros");
            } else {
                resultado.setValue(res); // Ej: "1.00 EUR = 1.09 USD"
            }
        } else {
            mensaje.setValue("Seleccione una dirección de conversión");
        }
    }

}
