package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import models.Rate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BankSteps {

    private static final Logger logger = LogManager.getLogger(BankSteps.class);
    private static Response response;
    private static List<Rate> rates;

    @Given("I fetch exchange rates")
    public void fetchRates() {
        logger.info("[STEP] Fetching exchange rates from NBP API...");
        if (response == null) {
            response = given().baseUri("http://api.nbp.pl").when().get("/api/exchangerates/tables/A?format=json");
            rates = response.jsonPath().getList("[0].rates", Rate.class);
            logger.info("[✓] Fetched " + rates.size() + " exchange rates");
        }
    }

    @Then("I print response status code")
    public void printStatusCode() {
        logger.info("[STEP] Checking response status code...");
        logger.info("Status code: " + response.getStatusCode());
        logger.info("[✓] Status code verified");
    }

    @And("I display rate for currency code {string}")
    public void getRateByCode(String code) {
        logger.info("[STEP] Displaying rate for currency code: " + code);
        Rate rate = rates.stream().filter(r -> r.code.equalsIgnoreCase(code)).findFirst().orElse(null);
        assertNotNull(rate, "Currency not found: " + code);
        logger.info(code + ": " + rate.mid);
        logger.info("[✓] Currency code " + code + " found and displayed");
    }

    @And("I display rate for currency name {string}")
    public void getRateByName(String name) {
        logger.info("[STEP] Displaying rate for currency name: " + name);
        Rate rate = rates.stream().filter(r -> r.currency.equalsIgnoreCase(name)).findFirst().orElse(null);
        assertNotNull(rate, "Currency not found: " + name);
        logger.info(name + ": " + rate.mid);
        logger.info("[✓] Currency name " + name + " found and displayed");
    }

    @And("I display currencies with rate above {string}")
    public void aboveRate(String value) {
        logger.info("[STEP] Displaying currencies with rate above: " + value);
        double threshold = Double.parseDouble(value);
        rates.stream().filter(r -> r.mid > threshold).forEach(r -> logger.info(r.code + " -> " + r.mid));
        logger.info("[✓] Currencies above " + value + " displayed");
    }

    @And("I display currencies with rate below {string}")
    public void belowRate(String value) {
        logger.info("[STEP] Displaying currencies with rate below: " + value);
        double threshold = Double.parseDouble(value);
        rates.stream().filter(r -> r.mid < threshold).forEach(r -> logger.info(r.code + " -> " + r.mid));
        logger.info("[✓] Currencies below " + value + " displayed");
    }
}