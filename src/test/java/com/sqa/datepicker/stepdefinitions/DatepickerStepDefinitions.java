package com.sqa.datepicker.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.sqa.datepicker.tasks.NavigateToDatepicker;
import com.sqa.datepicker.tasks.SelectDateFromCalendar;
import com.sqa.datepicker.tasks.AttemptManualDateInput;
import com.sqa.datepicker.questions.SelectedDate;
import com.sqa.datepicker.questions.FieldEditability;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

public class DatepickerStepDefinitions {

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void tearDown() {
        if (OnStage.theActorInTheSpotlight() != null) {
            try {
                WebDriver driver = BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver();
                if (driver != null) {
                    driver.quit();
                }
            } catch (Exception e) {
                // Ignorar errores al cerrar el driver
            }
        }
        OnStage.drawTheCurtain();
    }

    @Dado("que el usuario navega a la página principal de jQuery Datepicker")
    public void queElUsuarioNavegaALaPaginaPrincipalDeJQueryDatepicker() {
        Actor usuario = OnStage.theActorCalled("Usuario");

        // Configurar WebDriverManager para gestión automática de drivers
        WebDriverManager.chromedriver().setup();

        // Configurar WebDriver con opciones actualizadas
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Verificar si se debe ejecutar en modo headless
        String headlessMode = System.getProperty("headless.mode", "false");
        if (Boolean.parseBoolean(headlessMode)) {
            options.addArguments("--headless=new"); // Usar nueva sintaxis headless
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        usuario.can(BrowseTheWeb.with(driver));

        usuario.attemptsTo(
            NavigateToDatepicker.page()
        );
    }

    @Cuando("el usuario hace clic en el campo de selección de fecha")
    public void elUsuarioHaceClicEnElCampoDeSeleccionDeFecha() {
        // Esta acción se realiza dentro de SelectDateFromCalendar
    }

    @Y("selecciona el día {int} del mes actual")
    public void seleccionaElDiaDelMesActual(int dia) {
        theActorInTheSpotlight().attemptsTo(
            SelectDateFromCalendar.day(String.valueOf(dia))
        );
    }

    @Y("navega hasta el próximo mes")
    public void navegaHastaElProximoMes() {
        // Esta navegación se maneja en SelectDateFromCalendar.dayFromNextMonth
    }

    @Y("selecciona el día {int} del próximo mes")  
    public void seleccionaElDiaDelProximoMes(int dia) {
        theActorInTheSpotlight().attemptsTo(
            SelectDateFromCalendar.dayFromNextMonth(String.valueOf(dia))
        );
    }

    @Entonces("la fecha seleccionada debe aparecer en el campo de texto")
    public void laFechaSeleccionadaDebeAparecerEnElCampoDeTexto() {
        theActorInTheSpotlight().should(
            seeThat("La fecha fue seleccionada", 
                SelectedDate.value(), 
                not(isEmptyOrNullString())
            )
        );
    }

    @Cuando("el usuario intenta ingresar una fecha manualmente en el campo de texto")
    public void elUsuarioIntentaIngresarUnaFechaManualmenteEnElCampoDeTexto() {
        theActorInTheSpotlight().attemptsTo(
            AttemptManualDateInput.withText("01/01/2024")
        );
    }

    @Entonces("el campo no debe permitir la edición manual")
    public void elCampoNoDebePermitirLaEdicionManual() {
        theActorInTheSpotlight().should(
            seeThat("El campo no permite edición manual", 
                FieldEditability.ofDateField(), 
                is(false)
            )
        );
    }

    @Y("solo debe ser posible seleccionar una fecha desde el calendario")
    public void soloDebeSerPosibleSeleccionarUnaFechaDesdeElCalendario() {
        // Esta validación se cumple con la verificación anterior
        // El campo readonly impide la edición manual
    }
}