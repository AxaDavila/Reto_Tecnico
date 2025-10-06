package com.sqa.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.sqa.datepicker.interactions.SwitchToDatepickerFrame;
import static com.sqa.datepicker.ui.DatepickerPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import java.util.List;

public class AttemptManualDateInput implements Task {
    
    private final String dateText;
    
    public AttemptManualDateInput(String dateText) {
        this.dateText = dateText;
    }
    
    public static AttemptManualDateInput withText(String dateText) {
        return instrumented(AttemptManualDateInput.class, dateText);
    }
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            actor.attemptsTo(SwitchToDatepickerFrame.inside(DATEPICKER_IFRAME));
        } catch (Exception e) {
            // Continuar sin iframe
        }
        
        try {
            // MISMA estrategia exitosa
            List<WebElement> allInputs = BrowseTheWeb.as(actor).getDriver().findElements(By.tagName("input"));
            
            for (WebElement input : allInputs) {
                String value = input.getAttribute("value");
                if (value != null && value.contains("2025")) {
                    System.out.println("🔍 Input encontrado para entrada manual: '" + value + "'");
                    input.clear();
                    input.sendKeys(dateText);
                    System.out.println("✅ Texto enviado: '" + dateText + "'");
                    return;
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error en entrada manual: " + e.getMessage());
        }
    }
}
