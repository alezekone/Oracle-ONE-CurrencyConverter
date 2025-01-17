package ar.com.alezek.currencyconverter.principal;

import ar.com.alezek.currencyconverter.modelos.Conversor;
import ar.com.alezek.currencyconverter.modelos.ConversorFromExchangeRateAPI;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Hola Currency Converter !!!");

        String origen = "USD";
        String destino = "ARS";
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/1983c3cae5adae371dcacfce/pair/"+origen+"/"+destino);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

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

            System.out.println("Ahora serializo el objeto con toJson para ver como quedaría si quisiera enviarlo:");
            System.out.println(gson.toJson(miConversor, Conversor.class));
            System.out.println("Lo anterior es interesante. Vemos que Gson efectivamente usó la FieldNamePolicy, pero...");
            System.out.println("lo hizo sobre nuestro objeto de clase 'miConversor', como es lógico, porque no sabe del DTO.");
            System.out.println("Supongo entonces que, si quisiera enviar mi objeto (si fuera una posibilidad, que no lo es) de regreso");
            System.out.println("a ExchangeRate-API, tendría que primero convertirlo a mi DTO, y luego serializar ese DTO con GSON. ");

        } catch (Exception e) {
            throw new RuntimeException("La conversión no fue posible." + e.getMessage());
        }

    }
}
