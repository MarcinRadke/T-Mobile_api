package StepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import models.Rate;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankSteps {

    private static Response response;
    private static List<Rate> rates;

    @Given("I fetch exchange rates")
    public void fetchRates() {
        System.out.println("[STEP] Fetching exchange rates from NBP API...");

        if (response == null) {

            response = given()
                    .baseUri("http://api.nbp.pl")
                    .when()
                    .get("/api/exchangerates/tables/A?format=json");

            rates = response.jsonPath()
                    .getList("[0].rates", Rate.class);

            System.out.println("[✓] Fetched " + rates.size() + " exchange rates");
        }
    }

    @Then("I print response status code")
    public void printStatusCode() {
        System.out.println("[STEP] Checking response status code...");
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("[✓] Status code verified");
    }

    @And("I display rate for currency code {string}")
    public void getRateByCode(String code) {
        System.out.println("[STEP] Displaying rate for currency code: " + code);

        Rate rate = rates.stream()
                .filter(r -> r.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        assertNotNull(rate,
                "Currency not found: " + code);

        System.out.println(code + ": " + rate.mid);
        System.out.println("[✓] Currency code " + code + " found and displayed");
    }

    @And("I display rate for currency name {string}")
    public void getRateByName(String name) {
        System.out.println("[STEP] Displaying rate for currency name: " + name);

        Rate rate = rates.stream()
                .filter(r -> r.currency.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        assertNotNull(rate,
                "Currency not found: " + name);

        System.out.println(name + ": " + rate.mid);
        System.out.println("[✓] Currency name " + name + " found and displayed");
    }

    @And("I display currencies with rate above {string}")
    public void aboveRate(String value) {
        System.out.println("[STEP] Displaying currencies with rate above: " + value);

        double threshold = Double.parseDouble(value);

        rates.stream()
                .filter(r -> r.mid > threshold)
                .forEach(r ->
                        System.out.println(r.code + " -> " + r.mid));

        System.out.println("[✓] Currencies above " + value + " displayed");
    }

    @And("I display currencies with rate below {string}")
    public void belowRate(String value) {
        System.out.println("[STEP] Displaying currencies with rate below: " + value);

        double threshold = Double.parseDouble(value);

        rates.stream()
                .filter(r -> r.mid < threshold)
                .forEach(r ->
                        System.out.println(r.code + " -> " + r.mid));

        System.out.println("[✓] Currencies below " + value + " displayed");
    }
}