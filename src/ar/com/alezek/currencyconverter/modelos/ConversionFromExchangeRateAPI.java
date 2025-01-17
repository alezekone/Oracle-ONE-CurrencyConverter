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

public record ConversionFromExchangeRateAPI(String baseCode, String targetCode, double conversionRate) {
}
