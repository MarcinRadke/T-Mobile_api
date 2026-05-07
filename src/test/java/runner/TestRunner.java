package runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("Features")

@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "StepDefinitions"
)

public class TestRunner {
}