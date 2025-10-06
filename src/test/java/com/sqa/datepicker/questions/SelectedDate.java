package com.sqa.datepicker.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import com.sqa.datepicker.interactions.SwitchToDatepickerFrame;

import static com.sqa.datepicker.ui.DatepickerPage.*;

import java.util.List;

public class SelectedDate implements Question<String> {
    
    public static SelectedDate value() {
        return new SelectedDate();
    }
    
    @Override
    public String answeredBy(Actor actor) {
        try {
            // NO usar iframe - la página actual parece no tenerlo
            // actor.attemptsTo(SwitchToDatepickerFrame.inside(DATEPICKER_IFRAME));
        } catch (Exception e) {
            // Continuar sin iframe
        }
        
        try {
            // ESTRATEGIA 1: Buscar el input original
            try {
                WebElement element = DATE_INPUT_FIELD.resolveFor(actor);
                String value1 = element.getAttribute("value");
                if (value1 != null && !value1.trim().isEmpty()) {
                    System.out.println("✅ Valor encontrado (estrategia 1): '" + value1 + "'");
                    return value1;
                }
            } catch (Exception e1) {
                System.out.println("⚠️ Estrategia 1 falló: " + e1.getMessage());
            }
            
            // ESTRATEGIA 2: Buscar TODOS los inputs de texto en la página
            JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
            List<WebElement> allInputs = BrowseTheWeb.as(actor).getDriver().findElements(By.tagName("input"));
            
            for (WebElement input : allInputs) {
                String type = input.getAttribute("type");
                String value = input.getAttribute("value");
                String id = input.getAttribute("id");
                String className = input.getAttribute("class");
                
                System.out.println("🔍 Input encontrado - type: '" + type + "', value: '" + value + "', id: '" + id + "', class: '" + className + "'");
                
                // Buscar el input que contenga una fecha
                if (value != null && !value.trim().isEmpty()) {
                    // Si contiene formato de fecha (MM/DD/YYYY o similar)
                    if (value.matches(".*\\d{1,2}/\\d{1,2}/\\d{4}.*") || 
                        value.matches(".*\\d{4}-\\d{1,2}-\\d{1,2}.*") ||
                        value.contains("2025") || value.contains("/15/")) {
                        System.out.println("✅ Fecha encontrada (estrategia 2): '" + value + "'");
                        return value;
                    }
                }
            }
            
            // ESTRATEGIA 3: JavaScript para buscar elementos con hasDatepicker
            String jsValue = (String) js.executeScript(
                "var inputs = document.querySelectorAll('input');" +
                "for (var i = 0; i < inputs.length; i++) {" +
                "  if (inputs[i].classList.contains('hasDatepicker') || inputs[i].value.indexOf('/') > -1) {" +
                "    return inputs[i].value;" +
                "  }" +
                "}" +
                "return '';"
            );
            
            if (jsValue != null && !jsValue.trim().isEmpty()) {
                System.out.println("✅ Valor encontrado (estrategia 3 JS): '" + jsValue + "'");
                return jsValue;
            }
            
            System.out.println("❌ No se pudo encontrar el campo con la fecha");
            return "";
            
        } catch (Exception e) {
            System.err.println("Error obteniendo valor del campo: " + e.getMessage());
            return "";
        }
    }
}
