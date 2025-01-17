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

import static ar.com.alezek.currencyconverter.modelos.Moneda.*;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Hola Currency Converter !!!");

        Conversor miConversor = new Conversor(USD, ARS);
        miConversor.inicializar();
        double importeEnMonedaDestino = miConversor.convertir(100.00);
        System.out.println("100 USD equivalen a " + importeEnMonedaDestino + " pesos argentinos.");

    }
}
