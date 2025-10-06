package com.sqa.datepicker.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SwitchToDatepickerFrame implements Interaction {
    
    private final Target frameTarget;
    
    public SwitchToDatepickerFrame(Target frameTarget) {
        this.frameTarget = frameTarget;
    }
    
    public static SwitchToDatepickerFrame inside(Target frameTarget) {
        return instrumented(SwitchToDatepickerFrame.class, frameTarget);
    }
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();
            
            // Intentar cambiar al iframe solo si existe
            var frameElement = frameTarget.resolveFor(actor);
            driver.switchTo().frame(frameElement);
            System.out.println("✅ Iframe encontrado y cambiado");
        } catch (Exception e) {
            // La página actual de jQuery UI no tiene iframe, continuar en contexto principal
            System.out.println("ℹ️ Sin iframe - página directa de jQuery UI");
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();
            driver.switchTo().defaultContent();
        }
    }
}
