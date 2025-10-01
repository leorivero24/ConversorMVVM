package com.example.conversor.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConversorViewModel extends ViewModel {

    private final MutableLiveData<String> resultado = new MutableLiveData<>("");
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();

    // RadioButtons
    private final MutableLiveData<Boolean> radioDolarAEuroSeleccionado = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> radioEuroADolarSeleccionado = new MutableLiveData<>(false);

    private final MutableLiveData<Boolean> direccionConversion = new MutableLiveData<>(); // true=Dólar→Euro, false=Euro→Dólar

    private String valorDolar = "";
    private String valorEuro = "";

    // Getters LiveData
    public LiveData<String> getResultado() { return resultado; }
    public LiveData<String> getMensaje() { return mensaje; }
    public LiveData<Boolean> getRadioDolarAEuroSeleccionado() { return radioDolarAEuroSeleccionado; }
    public LiveData<Boolean> getRadioEuroADolarSeleccionado() { return radioEuroADolarSeleccionado; }
    public LiveData<Boolean> getDireccionConversion() { return direccionConversion; }

    // Valores de entrada
    public void setValorDolar(String valorDolar) { this.valorDolar = valorDolar; }
    public void setValorEuro(String valorEuro) { this.valorEuro = valorEuro; }

    // Selección de RadioButtons
    public void seleccionarDolarAEuro() {
        radioDolarAEuroSeleccionado.setValue(true);
        radioEuroADolarSeleccionado.setValue(false);
        direccionConversion.setValue(true);
    }

    public void seleccionarEuroADolar() {
        radioDolarAEuroSeleccionado.setValue(false);
        radioEuroADolarSeleccionado.setValue(true);
        direccionConversion.setValue(false);
    }

    // Lógica de conversión
    public void convertir() {
        if (direccionConversion.getValue() == null) {
            mensaje.setValue("Seleccione una dirección de conversión");
            return;
        }

        try {
            if (direccionConversion.getValue()) { // Dólar → Euro
                if (valorDolar.isEmpty()) {
                    mensaje.setValue("Ingrese un valor en dólares");
                    return;
                }
                double res = Double.parseDouble(valorDolar) * 0.92; // ejemplo
                resultado.setValue(String.format("%.2f €", res));
            } else { // Euro → Dólar
                if (valorEuro.isEmpty()) {
                    mensaje.setValue("Ingrese un valor en euros");
                    return;
                }
                double res = Double.parseDouble(valorEuro) * 1.09; // ejemplo
                resultado.setValue(String.format("%.2f $", res));
            }
        } catch (NumberFormatException e) {
            mensaje.setValue("Valor inválido");
        }
    }
}
