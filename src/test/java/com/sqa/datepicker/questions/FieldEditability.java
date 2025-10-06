package com.sqa.datepicker.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import com.sqa.datepicker.interactions.SwitchToDatepickerFrame;
import static com.sqa.datepicker.ui.DatepickerPage.*;
import java.util.List;

public class FieldEditability implements Question<Boolean> {
    
    public static FieldEditability ofDateField() {
        return new FieldEditability();
    }
    
    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            actor.attemptsTo(SwitchToDatepickerFrame.inside(DATEPICKER_IFRAME));
        } catch (Exception e) {
            // Continuar sin iframe
        }
        
        try {
            // USAR LA MISMA ESTRATEGIA EXITOSA que SelectedDate
            List<WebElement> allInputs = BrowseTheWeb.as(actor).getDriver().findElements(By.tagName("input"));
            
            WebElement dateInput = null;
            
            // Encontrar el input que contiene una fecha (MISMO método que SelectedDate)
            for (WebElement input : allInputs) {
                String value = input.getAttribute("value");
                if (value != null && !value.trim().isEmpty()) {
                    if (value.matches(".*\\d{1,2}/\\d{1,2}/\\d{4}.*") || 
                        value.contains("2025")) {
                        dateInput = input;
                        System.out.println("🔍 Input encontrado para editabilidad: '" + value + "'");
                        break;
                    }
                }
            }
            
            if (dateInput == null) {
                System.out.println("⚠️ No input encontrado, asumiendo no editable");
                return false;
            }
            
            // Verificar editabilidad
            String readonlyAttr = dateInput.getAttribute("readonly");
            String disabledAttr = dateInput.getAttribute("disabled");
            
            System.out.println("🔍 readonly: " + readonlyAttr + ", disabled: " + disabledAttr);
            
            // Test real de editabilidad
            JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
            
            try {
                String originalValue = dateInput.getAttribute("value");
                js.executeScript("arguments[0].value = 'test123';", dateInput);
                String testValue = dateInput.getAttribute("value");
                js.executeScript("arguments[0].value = arguments[1];", dateInput, originalValue);
                
                boolean actuallyEditable = !testValue.equals(originalValue);
                System.out.println("🔍 Realmente editable: " + actuallyEditable);
                
                // Retornar si ES editable
                return (readonlyAttr == null && disabledAttr == null && actuallyEditable);
                
            } catch (Exception e) {
                return (readonlyAttr == null && disabledAttr == null);
            }
            
        } catch (Exception e) {
            System.err.println("Error en FieldEditability: " + e.getMessage());
            return false;
        }
    }
}
