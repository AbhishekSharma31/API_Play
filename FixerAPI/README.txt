CONTENTS 

Fixer: Fixer.io is a free JSON API for current and historical foreign exchange rates published by the European Central Bank.
The rates are updated daily around 4PM CET.

EuroFixer.java

SpecificRate.java

ValueAnyDay.java

ValueAnyBase.java


FUNCTIONS WITH THE FILES1.EuroFixer.java

Get the latest foreign exchange reference rates in JSON format.

Sample Call.

{
    "date": "2016-06-08",
    "rates": {
        "CHF": 1.0942,
        "HRK": 7.5285,
        "MXN": 20.682,
        "ZAR": 16.7612,
        "INR": 75.721,
        "CNY": 7.4727,
        "THB": 39.994,
        "AUD": 1.5228,
        "ILS": 4.3531,
        "KRW": 1311.38,
        "JPY": 121.77,
        "PLN": 4.3228,
        "GBP": 0.78048,
        "IDR": 15025.79,
        "HUF": 310.09,
        "PHP": 52.223,
        "TRY": 3.2799,
        "RUB": 72.5458,
        "HKD": 8.8338,
        "DKK": 7.4364,
        "CAD": 1.443,
        "MYR": 4.5811,
        "USD": 1.1378,
        "BGN": 1.9558,
        "NOK": 9.2055,
        "RON": 4.5064,
        "SGD": 1.5337,
        "CZK": 27.021,
        "SEK": 9.2283,
        "NZD": 1.6226,
        "BRL": 3.8786
    },
    "base": "EUR"
}


2. SpecificRate.java

Request specific exchange rates by setting the symbols parameter.

Sample Output: If we pass USD and EUR as 2 arguments.

{
    "date": "2016-06-08",
    "rates": {"USD": 1.1378},
    "base": "EUR"
}


3. ValueAnyDay.java 

Get historical rates for any day since 1999.

Sample Output: If we pass 2010-04-01 as argument.

{
    "date": "2010-04-01",
    "rates": {
        "CHF": 1.4179,
        "HRK": 7.2638,
        "MXN": 16.6195,
        "LVL": 0.7089,
        "LTL": 3.4528,
        "ZAR": 9.8023,
        "INR": 60.202,
        "CNY": 9.1937,
        "THB": 43.609,
        "AUD": 1.4685,
        "KRW": 1517.04,
        "JPY": 126.28,
        "PLN": 3.8458,
        "GBP": 0.88485,
        "IDR": 12249.89,
        "HUF": 264.78,
        "PHP": 60.838,
        "TRY": 2.0481,
        "RUB": 39.5985,
        "HKD": 10.4604,
        "DKK": 7.4448,
        "CAD": 1.3622,
        "MYR": 4.3885,
        "USD": 1.3468,
        "BGN": 1.9558,
        "EEK": 15.6466,
        "NOK": 8.0168,
        "RON": 4.1081,
        "SGD": 1.8826,
        "CZK": 25.385,
        "SEK": 9.7288,
        "NZD": 1.9129,
        "BRL": 2.3953
    },
    "base": "EUR"
}


4. ValueAnyBase.java

Rates are quoted against the Euro by default. Quote against a different currency by setting the base parameter in your request.

Sample Output: If we pass INR as argument.

{
    "date": "2016-06-08",
    "rates": {
        "CHF": 0.01445,
        "HRK": 0.099424,
        "MXN": 0.27313,
        "ZAR": 0.22135,
        "CNY": 0.098687,
        "THB": 0.52818,
        "AUD": 0.020111,
        "ILS": 0.057489,
        "KRW": 17.319,
        "JPY": 1.6081,
        "PLN": 0.057089,
        "GBP": 0.010307,
        "IDR": 198.44,
        "HUF": 4.0952,
        "PHP": 0.68968,
        "TRY": 0.043316,
        "RUB": 0.95807,
        "HKD": 0.11666,
        "EUR": 0.013206,
        "DKK": 0.098208,
        "CAD": 0.019057,
        "MYR": 0.0605,
        "USD": 0.015026,
        "BGN": 0.025829,
        "NOK": 0.12157,
        "RON": 0.059513,
        "SGD": 0.020255,
        "CZK": 0.35685,
        "SEK": 0.12187,
        "NZD": 0.021429,
        "BRL": 0.051222
    },
    "base": "INR"
}