package com.example.conversor.model;

public class Conversor {

    private String valorDolar = "";
    private String valorEuro = "";

    private static final double TASA_DOLAR_EURO = 0.92; // ejemplo
    private static final double TASA_EURO_DOLAR = 1.09;

    public void setValorDolar(String valorDolar) {
        this.valorDolar = valorDolar;
    }

    public void setValorEuro(String valorEuro) {
        this.valorEuro = valorEuro;
    }

    public String getValorDolar() {
        return valorDolar;
    }

    public String getValorEuro() {
        return valorEuro;
    }

    public String convertirDolarAEuro() {
        if (valorDolar.isEmpty()) return "";
        double dolar = Double.parseDouble(valorDolar);
        double res = dolar * TASA_DOLAR_EURO;
        return String.format("%.2f USD = %.2f EUR", dolar, res);
    }

    public String convertirEuroADolar() {
        if (valorEuro.isEmpty()) return "";
        double euro = Double.parseDouble(valorEuro);
        double res = euro * TASA_EURO_DOLAR;
        return String.format("%.2f EUR = %.2f USD", euro, res);
    }
}
