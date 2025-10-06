package com.sqa.datepicker.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/datepicker.feature",
    glue = "com.sqa.datepicker.stepdefinitions",
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber-report.json",
        "html:target/cucumber-reports/cucumber-html-report",
        "junit:target/cucumber-reports/cucumber-results.xml",
        "timeline:target/cucumber-reports/timeline"
    },
    tags = "@scenario1 or @scenario2 or @scenario3",
    monochrome = true
)
public class DatepickerTestRunner {
    // Todos los reportes en target/cucumber-reports/
}
