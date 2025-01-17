package ar.com.alezek.currencyconverter.modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {
    String origen;
    String destino;
    double tipoDeCambio = 0;
    transient URI direccion;
    transient HttpClient client;
    transient HttpRequest request;
    transient Gson gson;

    public Conversor(Moneda origen, Moneda destino) {
        this.origen = origen.name();
        this.destino = destino.name();
        // this.tipoDeCambio = tipoDeCambio;
    }

    public Conversor(ConversorFromExchangeRateAPI conversor) {
        this.origen = conversor.baseCode();
        this.destino = conversor.targetCode();
        this.tipoDeCambio = conversor.conversionRate();
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

    public void inicializar(){
        this.direccion = URI.create("https://v6.exchangerate-api.com/v6/1983c3cae5adae371dcacfce/pair/"+origen+"/"+destino);

        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        this.client = HttpClient.newHttpClient();
        this.request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

    }

    public double convertir(double importeEnMonedaOrigen){
        double importeEnMonedaDestino = 0;

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Respuesta obtenida:" + response.body());

            String jsonResponse = response.body();

            ConversorFromExchangeRateAPI miConversorFromExchangeRateAPI = gson.fromJson(jsonResponse, ConversorFromExchangeRateAPI.class);
            System.out.println("Respuesta obtenida, i.e. estoy imprimiendo un objeto (implícitamente estoy usando toString()): ");
            System.out.println(miConversorFromExchangeRateAPI);

            Conversor miConversor = new Conversor(miConversorFromExchangeRateAPI);
            System.out.println("Ahora imprimiré el objeto, como lo quiero para operar con él:");
            System.out.println(miConversor);
            this.tipoDeCambio = miConversor.getTipoDeCambio();

            System.out.println("Ahora serializo el objeto con toJson para ver como quedaría si quisiera enviarlo:");
            System.out.println(gson.toJson(miConversor, Conversor.class));
            System.out.println("Lo anterior es interesante. Vemos que Gson efectivamente usó la FieldNamePolicy, pero...");
            System.out.println("lo hizo sobre nuestro objeto de clase 'miConversor', como es lógico, porque no sabe del DTO.");
            System.out.println("Supongo entonces que, si quisiera enviar mi objeto (si fuera una posibilidad, que no lo es) de regreso");
            System.out.println("a ExchangeRate-API, tendría que primero convertirlo a mi DTO, y luego serializar ese DTO con GSON. ");

            return importeEnMonedaOrigen*this.getTipoDeCambio();

        } catch (Exception e) {
            throw new RuntimeException("La conversión no fue posible." + e.getMessage());
        }
    }

}
