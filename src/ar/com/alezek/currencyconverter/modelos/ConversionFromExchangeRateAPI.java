package ar.com.alezek.currencyconverter.modelos;

/*
    {
        "result": "success",
        "documentation": "https://www.exchangerate-api.com/docs",
        "terms_of_use": "https://www.exchangerate-api.com/terms",
        "time_last_update_unix": 1736985601,
        "time_last_update_utc": "Thu, 16 Jan 2025 00:00:01 +0000",
        "time_next_update_unix": 1737072001,
        "time_next_update_utc": "Fri, 17 Jan 2025 00:00:01 +0000",
        "base_code": "USD",
        "target_code": "ARS",
        "conversion_rate": 1041.7500
    }
*/

/*
    ¿Por qué creamos este "record"? Aquí las razones:
    1.- Vemos que los campos del JSON que entrega esta API de ExchangeRate
    (i.e. base_code, taget_code, conversion_rate, entre otros) son diferentes
    al nombre de los campos de nuestra clase "Conversion" (i.e. origen, destino,
    tipoDeCambio). Esto podría resolverse mediante anotaciones, por ejemplo:
    en la clase "Conversion" podríamos anotar cada uno de nuestros atributos con
        @SerializedName("base_code")
        private String origen;
    2.- Una alternativa es usar DTOs, mediante un "record". Acá elegimos crear un record.
    Vemos que sus parámetros deben coincidir con los campos del JSON tal como se reciben,
    pero...eso no es lo que hice aquí, sino que en vez de base_code escribí baseCode !!!
    Bueno, eso es porque una buena práctica en Java es escribir los nombres de los atributos
    y variables en camel case, y nosotros no queremos ir contra las buenas prácticas. Para
    esto, recurro -en el builder de Gson- a:
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    Lo anterior me resolverá el problemita, adaptando los nombres de los campos del JSON a
    nuestros nombres del record.
    3.- Luego, en nuestra clase Conversion, creamos un nuevo constructor que permita crear
    instancias de esta clase a partir de un objeto de la clase ConversionFromExchangeRateAPI,
    y listo, ya tenemos nuestros objetos de clase Conversion construidos a partir del JSON
    recibido del servidor.
 */

public record ConversionFromExchangeRateAPI(String baseCode, String targetCode, double conversionRate) {
}
