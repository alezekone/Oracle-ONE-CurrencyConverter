package ar.com.alezek.currencyconverter.principal;

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

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            throw new RuntimeException("La conversión no fue posible.");
        }

    }
}
