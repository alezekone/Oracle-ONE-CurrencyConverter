package ar.com.alezek.currencyconverter.modelos;

public class Conversion {
    String origen;
    String destino;
    double tipoDeCambio;

    public Conversion (String origen, String destino, double tipoDeCambio) {
        this.origen = origen;
        this.destino = destino;
        this.tipoDeCambio = tipoDeCambio;
    }

    public Conversion (ConversionFromExchangeRateAPI conversion) {
        this.origen = conversion.baseCode();
        this.destino = conversion.targetCode();
        this.tipoDeCambio = conversion.conversionRate();
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getTipoDeCambio() {
        return tipoDeCambio;
    }

    @Override
    public String toString() {
        return "Conversion{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", tipoDeCambio=" + tipoDeCambio +
                '}';
    }

}
