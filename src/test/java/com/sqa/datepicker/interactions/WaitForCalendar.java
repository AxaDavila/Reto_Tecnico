package com.sqa.datepicker.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class WaitForCalendar implements Interaction {

    private final Target calendarTarget;

    public WaitForCalendar(Target calendarTarget) {
        this.calendarTarget = calendarTarget;
    }

    public static WaitForCalendar toBeVisible(Target calendarTarget) {
        return instrumented(WaitForCalendar.class, calendarTarget);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(calendarTarget, isVisible()).forNoMoreThan(30).seconds()
        );
    }
}