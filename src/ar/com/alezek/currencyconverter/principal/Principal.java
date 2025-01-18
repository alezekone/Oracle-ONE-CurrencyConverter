package ar.com.alezek.currencyconverter.principal;

import ar.com.alezek.currencyconverter.modelos.Conversor;
import ar.com.alezek.currencyconverter.modelos.ConversorFromExchangeRateAPI;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import static ar.com.alezek.currencyconverter.modelos.Moneda.*;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Bienvenido a mi conversor de moneda !!!");
        Conversor miConversor = null;
        int opcionDeConversion;
        double importeAConvertir;
        boolean salir = false;
        Scanner lectura = new Scanner(System.in);

        // *************  MENU  ***************
        while(!salir) {
            System.out.println("Sus opciones son:");
            System.out.println("1. Convertir un monto en USD (Dólares Estadounidenses) a ARS (Pesos Argentinos).");
            System.out.println("2. Convertir un monto en ARS (Pesos Argentinos) a USD (Dólares Estadounidenses).");
            System.out.println("3. Convertir un monto en USD (Dólares Estadounidenses) a BRL (Reales Brasileños).");
            System.out.println("4. Convertir un monto en BRL (Reales Brasileños) a USD (Dólares Estadounidenses).");
            System.out.println("5. Convertir un monto en USD (Dólares Estadounidenses) a CLP (Pesos Chilenos).");
            System.out.println("6. Convertir un monto en CLP (Pesos Chilenos) a USD (Dólares Estadounidenses).");
            System.out.println("7. Salir.");
            System.out.println("Indique la operación a realizar:");
            /* Analizamos la opción de conversión seleccionada y creamos el conversor correspondiente */
            try {
                opcionDeConversion = Integer.parseInt(lectura.nextLine());
                switch (opcionDeConversion) {
                    case 1:
                        miConversor = new Conversor(USD, ARS);
                        break;
                    case 2:
                        miConversor = new Conversor(ARS, USD);
                        break;
                    case 3:
                        miConversor = new Conversor(USD, BRL);
                        break;
                    case 4:
                        miConversor = new Conversor(BRL, USD);
                        break;
                    case 5:
                        miConversor = new Conversor(USD, CLP);
                        break;
                    case 6:
                        miConversor = new Conversor(CLP, USD);
                        break;
                    case 7:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no disponible.");
                }
                /* Si se eligió una opción de conversión válida, continuamos con la conversión. */
                if ((opcionDeConversion >= 1) && (opcionDeConversion <= 6)) {
                    System.out.println("Indique el monto a convertir:");
                    try {
                        importeAConvertir = Double.parseDouble(lectura.nextLine());
                        System.out.println("Consultando a la API...");
                        double importeEnMonedaDestino = miConversor.convertir(importeAConvertir);
                        System.out.println("El monto de " + importeAConvertir + " " + miConversor.getOrigen()
                            + " equivale a " + importeEnMonedaDestino + " " + miConversor.getDestino() + ".");
                        System.out.println();
                    } catch (NumberFormatException e) {
                        System.out.println("No es un monto válido / Formato incorrecto. " + e.getMessage());
                    } catch (RuntimeException e){
                        System.out.println("Finalizando la aplicación. Problema: " + e.getMessage());
                        salir = true;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida." + e.getMessage());
            }
        }

        System.out.println("Gracias por haber utilizado nuestros servicios.");

    }
}
