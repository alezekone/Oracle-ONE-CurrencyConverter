package ar.com.alezek.currencyconverter.modelos;

public class Conversor {
    String origen;
    String destino;
    double tipoDeCambio;

    public Conversor(String origen, String destino, double tipoDeCambio) {
        this.origen = origen;
        this.destino = destino;
        this.tipoDeCambio = tipoDeCambio;
    }

    public Conversor(ConversorFromExchangeRateAPI conversion) {
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
        return "Conversor{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", tipoDeCambio=" + tipoDeCambio +
                '}';
    }

}
