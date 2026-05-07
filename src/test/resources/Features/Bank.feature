# Marcin Radke
# 04.25.2026
# This feature file describes the scenario of showing exchange rates for specific currencies.

Feature: Showing exchange rates for specific currencies

  Scenario Outline: Show exchange rates for currencies with specific code, name, and exchange rate range
    Given I fetch exchange rates
    Then I print response status code
    And I display rate for currency code "<currency_code>"
    And I display rate for currency name "<currency_name>"
    And I display currencies with rate above "<exchange_rate_above>"
    And I display currencies with rate below "<exchange_rate_below>"

    Examples:
      | currency_code  | currency_name     | exchange_rate_above | exchange_rate_below |
      | USD            | dolar amerykański | 4                   | 3                   |